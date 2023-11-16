package com.resulgenc.moviehub.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class VideoData(
    val videoUri: String?,
    val drmLicenseUri: String?,
) : Parcelable