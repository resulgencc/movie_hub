package com.resulgenc.moviehub.data.enums

enum class SortBy(val value: String) {
    POPULARITY("popularity.desc"),
    PRIMARY_RELEASE_DATE("primary_release_date.desc"),
    REVENUE("revenue.desc")
}