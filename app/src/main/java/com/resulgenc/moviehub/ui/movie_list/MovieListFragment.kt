package com.resulgenc.moviehub.ui.movie_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import com.resulgenc.moviehub.R
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.databinding.FragmentMovieListBinding
import com.resulgenc.moviehub.ui.base_classes.BaseFragment
import com.resulgenc.moviehub.utils.Constants
import com.resulgenc.moviehub.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    private val binding by viewBinding(FragmentMovieListBinding::bind)

    private val viewModel by viewModels<MovieListViewModel>()

    private var popularAdapter: MovieListAdapter? = null
    private var releaseDateAdapter: MovieListAdapter? = null
    private var revenueAdapter: MovieListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.getMoviesByPopularity().collectLatest {
                popularAdapter?.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.getMoviesByReleaseDate().collectLatest {
                releaseDateAdapter?.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.getMoviesByRevenue().collectLatest {
                revenueAdapter?.submitData(it)
            }
        }
    }


    private fun initViews() {
        popularAdapter = MovieListAdapter(onMovieSelected = ::onMovieSelected)
        binding.popularRecyclerView.adapter = popularAdapter

        releaseDateAdapter = MovieListAdapter(onMovieSelected = ::onMovieSelected)
        binding.primaryReleaseDateRecyclerView.adapter = releaseDateAdapter

        revenueAdapter = MovieListAdapter(onMovieSelected = ::onMovieSelected)
        binding.revenueRecyclerView.adapter = revenueAdapter
    }

    private fun onMovieSelected(movie: Movie) {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.detail_nav_host) as NavHost
        val navController = navHostFragment.navController

        val bundle = bundleOf(Constants.SELECTED_MOVIE_ID to movie.id)
        navController.navigate(R.id.movieDetailFragment, bundle)

        binding.slidingPanelLayout.open()
    }
}