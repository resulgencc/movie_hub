package com.resulgenc.moviehub.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity class representing movie details stored in the local database.
 */
@Entity(tableName = "table_movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("is_adult") val isAdult: Boolean,
    @ColumnInfo("backdrop_path") val backdropPath: String,
    @ColumnInfo("movie_id") val movieId: Int,
    @ColumnInfo("original_language") val originalLanguage: String,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("overview") val popularity: Float,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("video") val video: Boolean,
    @ColumnInfo("vote_average") val voteAverage: Float,
    @ColumnInfo("vote_count") val voteCount: Int,
    @ColumnInfo("sort_by") val sortBy: String
)
