package com.resulgenc.moviehub.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.resulgenc.moviehub.data.local.dao.MovieDao
import com.resulgenc.moviehub.data.local.entities.MovieEntity


/**
 * Database class representing the local storage of movies.
 * Annotated with Room's @Database to define entities and database version.
 */
@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    /**
     * Provides access to MovieDao operations within the database.
     *
     * @return The MovieDao interface for movie-related database operations.
     */
    abstract fun moviesDao(): MovieDao

    companion object {
        const val MOVIES_DATABASE_NAME = "movies_db"
    }
}

