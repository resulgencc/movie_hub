package com.resulgenc.moviehub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.resulgenc.moviehub.data.local.entities.MovieEntity

/**
 * Data Access Object (DAO) interface for MovieEntity operations.
 * Contains methods for querying and modifying MovieEntity data in the local database.
 */
@Dao
interface MovieDao {

    /**
     * Retrieves movies from the local database sorted by the specified parameter.
     *
     * @param sortBy The sorting parameter.
     * @return A list of MovieEntity objects sorted by the specified parameter.
     */
    @Query("SELECT * FROM table_movies WHERE sort_by = :sortBy")
    suspend fun getMoviesSortedBy(sortBy: String): List<MovieEntity>

    /**
     * Inserts or updates a list of MovieEntity objects in the local database.
     *
     * @param movies The list of MovieEntity objects to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMovies(movies: List<MovieEntity>)
}
