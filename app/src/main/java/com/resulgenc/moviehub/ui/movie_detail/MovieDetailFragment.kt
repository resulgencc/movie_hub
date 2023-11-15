package com.resulgenc.moviehub.ui.movie_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.resulgenc.moviehub.R
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.databinding.FragmentMovieDetailBinding
import com.resulgenc.moviehub.ui.base_classes.BaseFragment
import com.resulgenc.moviehub.utils.Constants
import com.resulgenc.moviehub.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        parseArguments()
    }

    private fun parseArguments() {
        try {
            val bundle = arguments ?: return

            val selectedMovieId = bundle.getInt(
                Constants.SELECTED_MOVIE_ID,
                Constants.SELECTED_MOVIE_INVALID_VALUE
            )

            Timber.d("selected: $selectedMovieId")

            if (selectedMovieId != Constants.SELECTED_MOVIE_INVALID_VALUE) {
                viewModel.findMovieById(movieId = selectedMovieId)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun initObservers() {
        viewModel.selectedMovie.observe(viewLifecycleOwner) {
            initViews(movie = it)
        }
    }

    private fun initViews(movie: Movie) {
        binding.textView.text = movie.overview
    }
}