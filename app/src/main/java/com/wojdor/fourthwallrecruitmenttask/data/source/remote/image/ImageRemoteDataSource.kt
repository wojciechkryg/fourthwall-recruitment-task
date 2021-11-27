package com.wojdor.fourthwallrecruitmenttask.data.source.remote.image

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import javax.inject.Inject

class ImageRemoteDataSource @Inject constructor(
    private val imageLoader: ImageLoader,
    private val imageRequestBuilder: ImageRequest.Builder
) : ImageRemoteSource {

    override suspend fun downloadImage(imageUrl: String): Bitmap? {
        val request = imageRequestBuilder.data(imageUrl).build()
        return (imageLoader.execute(request).drawable as? BitmapDrawable)?.bitmap
    }
}