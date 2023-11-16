package com.resulgenc.moviehub.di.modules

import android.content.Context
import androidx.room.Room
import com.resulgenc.moviehub.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the MoviesDatabase Singleton instance
     */
    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MoviesDatabase::class.java,
            name = MoviesDatabase.MOVIES_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    /**
     * Provides the MovieDao instance from the MoviesDatabase
     */
    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase) =
        moviesDatabase.moviesDao()
}