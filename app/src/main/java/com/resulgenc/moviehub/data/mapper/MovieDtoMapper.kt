package com.resulgenc.moviehub.data.mapper

import com.resulgenc.moviehub.BuildConfig
import com.resulgenc.moviehub.data.model.Movie
import com.resulgenc.moviehub.data.network.dto.MovieDto
import com.resulgenc.moviehub.utils.Constants
import javax.inject.Inject

/**
 * Mapper class responsible for mapping MovieDto objects to Movie objects.
 *
 * @param constructor Injected constructor for dependency injection.
 */
class MovieDtoMapper @Inject constructor() {

    /**
     * Maps a single MovieDto object to a Movie object.
     *
     * @param dtoObject The MovieDto object to be mapped.
     * @return The corresponding Movie object.
     */
    fun mapFromDto(dtoObject: MovieDto): Movie {
        val backdropPath = BuildConfig.IMAGE_BASE_URL +
                Constants.ImagePrefixPath.ORIGINAL_PATH +
                dtoObject.backdropPath

        val posterPath = BuildConfig.IMAGE_BASE_URL +
                Constants.ImagePrefixPath.POSTER_PATH +
                dtoObject.posterPath

        return Movie(
            adult = dtoObject.isAdult,
            backdropPath = backdropPath,
            id = dtoObject.id,
            originalLanguage = dtoObject.originalLanguage,
            originalTitle = dtoObject.originalTitle,
            overview = dtoObject.overview,
            popularity = dtoObject.popularity,
            posterPath = posterPath,
            releaseDate = dtoObject.releaseDate,
            title = dtoObject.title,
            video = dtoObject.video,
            voteAverage = dtoObject.voteAverage,
            voteCount = dtoObject.voteCount
        )
    }

    /**
     * Maps a list of MovieDto objects to a list of Movie objects.
     *
     * @param list The list of MovieDto objects to be mapped.
     * @return The corresponding list of Movie objects.
     */
    fun mapFromDtoList(list: List<MovieDto>): List<Movie> {
        return list.map {
            mapFromDto(it)
        }
    }
}
