package com.wojdor.fourthwallrecruitmenttask.data.source.remote.image

import android.graphics.Bitmap

interface ImageRemoteSource {

    suspend fun downloadImage(imageUrl: String): Bitmap?
}