package com.wojdor.fourthwallrecruitmenttask.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoEntity(
    @Json(name = "id") val id: String?,
    @Json(name = "author") val author: String?,
    @Json(name = "download_url") val imageUrl: String?
)