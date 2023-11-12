package com.resulgenc.moviehub.data.network.dto

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class MovieDto(
    @SerializedName("adult") val isAdult: Boolean = false,
    @SerializedName("backdrop_path") val backdropPath: String = "",
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("id") val id: Int = 0,
    @SerializedName("original_language") val originalLanguage: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("popularity") val popularity: Float = 0.0f,
    @SerializedName("poster_path") val posterPath: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Float = 0.0f,
    @SerializedName("vote_count") val voteCount: Int = 0
) {

    /**
     * Overrides the default equals implementation for custom equality comparison based on the 'id' property.
     *
     * @param other The object to compare with this instance.
     * @return `true` if the 'id' properties are equal, otherwise `false`.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false

        other as MovieDto

        return id == other.id
    }


    /**
    * Overrides the default hashCode implementation to generate a hash based on the 'id' property.
    *
    * @return The hash code value.
    */
    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}