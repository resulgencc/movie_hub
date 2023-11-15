package com.resulgenc.moviehub.ui.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.databinding.AdapterItemMovieListBinding

class MovieListAdapter(
    private val onMovieSelected: ((Movie) -> Unit)
) : PagingDataAdapter<Movie, MovieListAdapter.MovieListAdapterViewHolder>(COMPARATOR) {


    inner class MovieListAdapterViewHolder(val binding: AdapterItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: MovieListAdapterViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            val text = "$position: ${movie.id} ${movie.title}"
            holder.binding.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemMovieListBinding.inflate(layoutInflater, parent, false)
        return MovieListAdapterViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }
}