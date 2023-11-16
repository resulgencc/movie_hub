package com.resulgenc.moviehub.data.network.dto

import com.google.gson.annotations.SerializedName

data class VideoDto(
    @SerializedName("name") val name: String?,
    @SerializedName("uri") val videoUri: String?,
    @SerializedName("drm_scheme") val drmScheme: String?,
    @SerializedName("drm_license_uri") val drmLicenseUri: String?,
) {

    companion object {
        fun dummyDto() = VideoDto(
            name = "HD (cenc)",
            videoUri = "https://storage.googleapis.com/wvmedia/cenc/h264/tears/tears.mpd",
            drmScheme = "widevine",
            drmLicenseUri = "https://proxy.uat.widevine.com/proxy?video_id=2015_tears&provider=widevine_test"
        )
    }
}

