package com.wojdor.fourthwallrecruitmenttask.data.source.local.file

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.wojdor.fourthwallrecruitmenttask.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class FileLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageFile: File
) : FileLocalSource {

    override suspend fun saveBitmapToFile(bitmap: Bitmap): Uri {
        val file = imageFile.apply {
            outputStream().use { bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_QUALITY, it) }
        }
        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file)
    }

    companion object {
        private const val BITMAP_QUALITY = 100
    }
}