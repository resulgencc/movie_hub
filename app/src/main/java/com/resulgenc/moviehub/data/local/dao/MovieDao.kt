package com.resulgenc.moviehub.data.local.dao

import androidx.paging.PagingSource
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
     * Inserts or updates a list of MovieEntity objects in the local database.
     *
     * @param movies The list of MovieEntity objects to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMovies(movies: List<MovieEntity>)


    /**
     * Retrieves a paging source for MovieEntity objects from the local database
     * based on the specified sorting parameter.
     *
     * @param sortBy The sorting parameter.
     * @return A PagingSource for MovieEntity objects based on the specified sorting parameter.
     */
    @Query("SELECT * FROM table_movies WHERE sort_by = :sortBy")
    fun getPagingSourceByCategory(sortBy: String): PagingSource<Int, MovieEntity>


    /**
     * Clears all MovieEntity objects with the specified sorting parameter from the local database.
     *
     * @param sortBy The sorting parameter of MovieEntity objects to be cleared.
     */
    @Query("DELETE FROM table_movies WHERE sort_by = :sortBy")
    suspend fun clearAllMovies(sortBy: String)


    /**
     * Retrieves the last MovieEntity object with the specified sorting parameter from the local database,
     * ordered by the 'current_page' field in descending order, limited to one result.
     *
     * @param sortBy The sorting parameter.
     * @return The last MovieEntity object based on the specified sorting parameter, ordered by 'current_page'.
     */
    @Query("SELECT * FROM table_movies WHERE sort_by = :sortBy ORDER BY current_page DESC LIMIT 1")
    suspend fun lastOne(sortBy: String): MovieEntity?


    /**
     * Retrieves the MovieEntity object with the specified ID from the local database.
     *
     * @param id The ID of the MovieEntity to retrieve.
     * @return The MovieEntity object with the specified ID, if found; otherwise, null.
     */
    @Query("SELECT * FROM table_movies WHERE id=:id")
    suspend fun findById(id: Int): MovieEntity?
}

