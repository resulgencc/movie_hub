package com.resulgenc.moviehub.data.network.services

import com.resulgenc.moviehub.data.network.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieDto
}