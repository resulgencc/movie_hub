package com.resulgenc.moviehub.data.network.dto

import com.google.gson.annotations.SerializedName


/**
* Data class representing the response containing a list of movies.
*
* @property page The current page of the response.
* @property movies The list of movies in the response.
* @property totalPages The total number of pages available.
* @property totalResults The total number of results available.
*/
data class AllMoviesResponse(
    @SerializedName(value = "page") val page: Int = 0,
    @SerializedName(value = "results") val movies: List<MovieDto> = emptyList(),
    @SerializedName(value = "total_pages") val totalPages: Int = 0,
    @SerializedName(value = "total_results") val totalResults: Int = 0
)