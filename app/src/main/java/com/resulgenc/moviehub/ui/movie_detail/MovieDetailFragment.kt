package com.resulgenc.moviehub.ui.movie_detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaItem.DrmConfiguration
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.resulgenc.moviehub.R
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.data.model.VideoData
import com.resulgenc.moviehub.databinding.FragmentMovieDetailBinding
import com.resulgenc.moviehub.ui.base_classes.BaseFragment
import com.resulgenc.moviehub.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.UUID


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail), Player.Listener {

    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModels<MovieDetailViewModel>()

    private val args: MovieDetailFragmentArgs by navArgs()

    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(requireContext()).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.selectedMovie
        initViews(movie)
    }

    private fun initViews(movie: Movie) {
        binding.movie = movie
        setPlayer(movie = movie)
    }

    private fun setPlayer(movie: Movie) {
        val mediaItem = getMediaItem(videoData = movie.videoData)

        binding.playerView.player = player

        player.apply {
            addListener(this@MovieDetailFragment)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    private fun getMediaItem(videoData: VideoData): MediaItem {
        val drmConfiguration = getDRMConfiguration(licenseUri = videoData.drmLicenseUri)

        return MediaItem.Builder()
            .setUri(videoData.videoUri)
            .setDrmConfiguration(drmConfiguration)
            .build()
    }

    private fun getDRMConfiguration(
        licenseUri: String?,
        scheme: UUID = C.WIDEVINE_UUID,
        multiSession: Boolean = true
    ): DrmConfiguration {
        return DrmConfiguration.Builder(scheme)
            .setLicenseUri(licenseUri)
            .setMultiSession(multiSession)
            .build()
    }

    // player listener functions

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                binding.progressBar.isVisible = true
            }

            Player.STATE_READY -> {
                binding.progressBar.isVisible = false
            }

            Player.STATE_ENDED -> {
            }

            Player.STATE_IDLE -> {
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        player.release()
        Timber.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }

}