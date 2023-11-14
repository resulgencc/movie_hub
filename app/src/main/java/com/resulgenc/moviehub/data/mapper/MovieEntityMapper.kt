package com.resulgenc.moviehub.data.mapper

import com.resulgenc.moviehub.data.local.entities.MovieEntity
import com.resulgenc.moviehub.data.model.Movie
import javax.inject.Inject

/**
 * Mapper class responsible for mapping between MovieEntity and Movie objects.
 * Converts MovieEntity objects to Movie objects and vice versa.
 * Annotated with @Inject for dependency injection.
 */
class MovieEntityMapper @Inject constructor() {

    /**
     * Maps a MovieEntity object to a Movie object.
     *
     * @param entity The MovieEntity object to be mapped.
     * @return The corresponding Movie object.
     */
    fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            isAdult = entity.isAdult,
            backdropPath = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }

    /**
     * Maps a list of MovieEntity objects to a list of Movie objects.
     *
     * @param entities The list of MovieEntity objects to be mapped.
     * @return The corresponding list of Movie objects.
     */
    fun mapFromEntityList(entities: List<MovieEntity>): List<Movie> {
        return entities.map {
            mapFromEntity(it)
        }
    }


    /**
     * Maps a Movie object to a MovieEntity object.
     *
     * @param movie The Movie object to be mapped.
     * @param sortBy The sorting parameter for the MovieEntity.
     * @return The corresponding MovieEntity object.
     */
    fun mapToEntity(movie: Movie, sortBy: String, currentPage: Int): MovieEntity {
        return MovieEntity(
            id = movie.id,
            isAdult = movie.isAdult,
            backdropPath = movie.backdropPath,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            sortBy = sortBy,
            currentPage = currentPage
        )
    }

    /**
     * Maps a list of Movie objects to a list of MovieEntity objects.
     *
     * @param movies The list of Movie objects to be mapped.
     * @param sortBy The sorting parameter for the MovieEntity list.
     * @return The corresponding list of MovieEntity objects.
     */
    fun mapToEntityList(movies: List<Movie>, sortBy: String, currentPage: Int): List<MovieEntity> {
        return movies.map {
            mapToEntity(it, sortBy, currentPage)
        }
    }
}