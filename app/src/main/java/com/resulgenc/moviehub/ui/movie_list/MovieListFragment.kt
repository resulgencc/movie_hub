package com.resulgenc.moviehub.ui.movie_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.resulgenc.moviehub.R
import com.resulgenc.moviehub.data.enums.SortBy
import com.resulgenc.moviehub.databinding.FragmentMovieListBinding
import com.resulgenc.moviehub.ui.base_classes.BaseFragment
import com.resulgenc.moviehub.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    private val binding by viewBinding(FragmentMovieListBinding::bind)

    private val viewModel by viewModels<MovieListViewModel>()

    private var adapter: MovieListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        lifecycleScope.launch {
            viewModel.getMoviesByCategory(sortBy = SortBy.POPULARITY).collect {
                adapter?.submitData(it)
            }
        }
    }


    private fun initViews() {
        adapter = MovieListAdapter {

        }

        binding.recyclerView.adapter = adapter
    }
}