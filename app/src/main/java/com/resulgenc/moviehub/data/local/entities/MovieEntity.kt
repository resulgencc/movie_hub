package com.resulgenc.moviehub.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity class representing movie details stored in the local database.
 */
@Entity(tableName = "table_movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0, // entity Id
    @ColumnInfo("movieId") val movieId: Int,
    @ColumnInfo("is_adult") val isAdult: Boolean,
    @ColumnInfo("backdrop_path") val backdropPath: String,
    @ColumnInfo("original_language") val originalLanguage: String,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("popularity") val popularity: Float,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("video") val video: Boolean,
    @ColumnInfo("vote_average") val voteAverage: Float,
    @ColumnInfo("vote_count") val voteCount: Int,
    @ColumnInfo("sort_by") val sortBy: String,
    @ColumnInfo("current_page") val currentPage: Int,
    @Embedded val videoData: VideoEntity
)
