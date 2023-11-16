package com.resulgenc.moviehub.data.network.services

import com.resulgenc.moviehub.data.network.dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListServices {


    /**
     * Retrieves a list of movies from the remote repository.
     *
     * @param includeAdult Whether to include adult content in the results. Default is false.
     * @param includeVideo Whether to include videos in the results. Default is true.
     * @param language The language for the results. Default is "en-US".
     * @param page The page number of the results. Default is 1.
     * @param sortBy The sorting order for the results. Default is "popularity.desc".
     *
     * @return An [MovieListResponse] representing the response containing the list of movies and
     * additional information.
     */

    @GET("movie")
    suspend fun getAllMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = true,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String
    ): MovieListResponse
}