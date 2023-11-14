package com.resulgenc.moviehub.data.network.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.resulgenc.moviehub.data.enums.SortBy
import com.resulgenc.moviehub.data.local.database.MoviesDatabase
import com.resulgenc.moviehub.data.local.entities.MovieEntity
import com.resulgenc.moviehub.data.mapper.MovieDtoMapper
import com.resulgenc.moviehub.data.mapper.MovieEntityMapper
import com.resulgenc.moviehub.data.network.services.MovieListServices
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class MovieListRemoteMediator(
    private val movieListServices: MovieListServices,
    private val moviesDatabase: MoviesDatabase,
    private val dtoMapper: MovieDtoMapper,
    private val entityMapper: MovieEntityMapper,
    private val sortBy: SortBy,
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.currentPage?.plus(1) ?: 1
                }
            }

            val response = movieListServices.getAllMovies(page = page, sortBy = sortBy.value)

            moviesDatabase.withTransaction {
                val moviesDao = moviesDatabase.moviesDao()

                if (loadType == LoadType.REFRESH) {
                    moviesDao.clearAllMovies(sortBy = sortBy.value)
                }

                val movies = dtoMapper.mapFromDtoList(response.movies)
                val entities = entityMapper.mapToEntityList(
                    movies = movies,
                    sortBy = sortBy.value,
                    currentPage = response.page
                )

                moviesDao.insertOrUpdateMovies(entities)
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)

        } catch (exception: Exception) {
            Timber.e(exception)
            MediatorResult.Error(exception)
        }
    }
}