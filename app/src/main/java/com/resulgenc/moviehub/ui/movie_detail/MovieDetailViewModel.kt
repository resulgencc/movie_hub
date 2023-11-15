package com.resulgenc.moviehub.ui.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resulgenc.moviehub.data.local.dao.MovieDao
import com.resulgenc.moviehub.data.mapper.MovieEntityMapper
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.ui.base_classes.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDao: MovieDao,
    private val entityMapper: MovieEntityMapper,
) : BaseViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie


    fun findMovieById(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = movieDao.findById(movieId) ?: return@launch
            val movie = entityMapper.mapFromEntity(entity)
            _selectedMovie.postValue(movie)
        }
    }

}