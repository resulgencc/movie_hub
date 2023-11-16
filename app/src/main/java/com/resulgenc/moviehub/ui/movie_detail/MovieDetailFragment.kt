package com.resulgenc.moviehub.ui.movie_detail

import android.content.res.Configuration
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
import java.util.UUID


@AndroidEntryPoint
/**
 * MovieDetailFragment represents a fragment displaying details of a movie and handling video playback.
 * Inherits from BaseFragment to manage system UI visibility.
 */
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail), Player.Listener {

    // ViewBinding for the layout
    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    // ViewModel for managing movie details
    private val viewModel by viewModels<MovieDetailViewModel>()

    // Arguments passed to the fragment
    private val args: MovieDetailFragmentArgs by navArgs()

    // ExoPlayer instance for video playback
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(requireContext()).build()
    }

    // Called when the fragment's view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets up system UI visibility based on device orientation
        setSystemUI()

        // Initializes observers for ViewModel data changes
        initObservers()

        // Retrieves movie details from arguments and initializes views
        val movie = args.selectedMovie
        initViews(movie)
    }

    // Sets the system UI visibility based on device orientation
    private fun setSystemUI() {
        val configuration = resources.configuration

        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showSystemUI(binding.root)
        } else {
            hideSystemUI(binding.root)
        }
    }

    // Initializes observers for ViewModel data changes
    private fun initObservers() {
        viewModel.currentPosition.observe(viewLifecycleOwner) {
            player.seekTo(it ?: 0L)
        }
    }

    // Initializes views with movie details
    private fun initViews(movie: Movie) {
        binding.movie = movie
        setPlayer(movie = movie)
    }

    // Configures ExoPlayer with media for playback
    private fun setPlayer(movie: Movie) {
        val mediaItem = getMediaItem(videoData = movie.videoData)

        binding.playerView.player = player

        player.apply {
            playWhenReady = true
            addListener(this@MovieDetailFragment)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    // Retrieves MediaItem for video playback
    private fun getMediaItem(videoData: VideoData): MediaItem {
        val drmConfiguration = getDRMConfiguration(licenseUri = videoData.drmLicenseUri)

        return MediaItem.Builder()
            .setUri(videoData.videoUri)
            .setDrmConfiguration(drmConfiguration)
            .build()
    }

    // Retrieves DRM configuration for secure video playback
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

    // Overrides onSaveInstanceState to save the current playback position
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.setCurrentPosition(player.currentPosition)
    }

    // Overrides onDestroyView to release the ExoPlayer instance
    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

    // Implements player listener functions for playback state changes
    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                binding.progressBarContainer.isVisible = true
            }

            Player.STATE_READY -> {
                binding.progressBarContainer.isVisible = false
            }

            // Handle other playback states as needed
            Player.STATE_ENDED -> {
                // Handle playback end
            }

            Player.STATE_IDLE -> {
                // Handle playback idle state
            }
        }
    }
}
