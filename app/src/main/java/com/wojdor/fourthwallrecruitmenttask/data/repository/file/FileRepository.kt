package com.wojdor.fourthwallrecruitmenttask.data.repository.file

import android.graphics.Bitmap
import android.net.Uri

interface FileRepository {

    suspend fun downloadImage(imageUrl: String): Bitmap?

    suspend fun saveImageToCache(bitmap: Bitmap): Uri?
}