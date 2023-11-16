package com.resulgenc.moviehub.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.resulgenc.moviehub.data.enums.SortBy
import com.resulgenc.moviehub.data.local.database.MoviesDatabase
import com.resulgenc.moviehub.data.local.entities.MovieEntity
import com.resulgenc.moviehub.data.mapper.MovieDtoMapper
import com.resulgenc.moviehub.data.mapper.MovieEntityMapper
import com.resulgenc.moviehub.data.network.mediator.MovieListRemoteMediator
import com.resulgenc.moviehub.data.network.services.MovieListServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieListRepository @Inject constructor(
    private val movieListServices: MovieListServices,
    private val moviesDatabase: MoviesDatabase,
    private val dtoMapper: MovieDtoMapper,
    private val entityMapper: MovieEntityMapper,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getMoviesByCategory(sortBy: SortBy): Flow<PagingData<MovieEntity>> {
        val movieDao = moviesDatabase.moviesDao()

        return Pager(
            config = createConfig(),
            remoteMediator = MovieListRemoteMediator(
                movieListServices = movieListServices,
                moviesDatabase = moviesDatabase,
                dtoMapper = dtoMapper,
                entityMapper = entityMapper,
                sortBy = sortBy
            ),
            pagingSourceFactory = { movieDao.getPagingSourceByCategory(sortBy = sortBy.value) },
        ).flow
    }


    private fun createConfig(
        pageSize: Int = 20,
        enablePlaceholders: Boolean = false
    ): PagingConfig {
        return PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = enablePlaceholders
        )
    }
}