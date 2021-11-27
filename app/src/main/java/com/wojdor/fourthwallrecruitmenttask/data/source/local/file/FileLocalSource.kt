package com.wojdor.fourthwallrecruitmenttask.data.source.local.file

import android.graphics.Bitmap
import android.net.Uri

interface FileLocalSource {

    suspend fun saveBitmapToFile(bitmap: Bitmap): Uri
}