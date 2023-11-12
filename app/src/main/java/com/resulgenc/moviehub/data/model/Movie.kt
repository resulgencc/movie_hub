package com.resulgenc.moviehub.data.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
) {

    /**
     * Generates a formatted string representing the vote average of the movie.
     *
     * @return A formatted string, e.g., "7.50/10".
     */
    fun vote(): String = String.format("%.02f/10", voteAverage)
}