package com.wojdor.fourthwallrecruitmenttask.data.repository.file

import android.graphics.Bitmap
import com.wojdor.fourthwallrecruitmenttask.data.source.local.file.FileLocalSource
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.image.ImageRemoteSource
import javax.inject.Inject

class FileDataRepository @Inject constructor(
    private val imageRemoteSource: ImageRemoteSource,
    private val fileLocalSource: FileLocalSource
) : FileRepository {

    override suspend fun downloadImage(imageUrl: String) = imageRemoteSource.downloadImage(imageUrl)

    override suspend fun saveImageToCache(bitmap: Bitmap) = fileLocalSource.saveBitmapToFile(bitmap)
}