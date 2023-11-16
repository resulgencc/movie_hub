package com.resulgenc.moviehub.ui.movie_detail

import androidx.lifecycle.SavedStateHandle
import com.resulgenc.moviehub.ui.base_classes.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
) : BaseViewModel() {


    // LiveData to observe and retrieve the current playback position
    val currentPosition = stateHandle.getLiveData<Long?>(CURRENT_POSITION)

    /**
     * Sets the current playback position in the SavedStateHandle.
     * @param time The current playback time to be saved.
     */
    fun setCurrentPosition(time: Long) {
        stateHandle[CURRENT_POSITION] = time
    }

    // Companion object holding the constant key for storing the current playback position
    private companion object {
        const val CURRENT_POSITION = "current_time"
    }
}