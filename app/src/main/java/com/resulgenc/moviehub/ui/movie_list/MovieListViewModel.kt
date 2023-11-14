package com.resulgenc.moviehub.ui.movie_list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.resulgenc.moviehub.data.enums.SortBy
import com.resulgenc.moviehub.data.mapper.MovieEntityMapper
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.data.repositories.MovieListRepository
import com.resulgenc.moviehub.ui.base_classes.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val entityMapper: MovieEntityMapper,
) : BaseViewModel() {

    fun getMoviesByCategory(sortBy: SortBy): Flow<PagingData<Movie>> =
        movieListRepository
            .getMoviesByCategory(sortBy = sortBy)
            .map { pagingData ->
                pagingData.map { movieEntity ->
                    entityMapper.mapFromEntity(movieEntity)
                }
            }.cachedIn(viewModelScope)
}