package com.wojdor.fourthwallrecruitmenttask.ui.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.wojdor.fourthwallrecruitmenttask.R

fun Context.shareJpegUri(uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/jpeg"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    startActivity(Intent.createChooser(intent, getString(R.string.share_image)))
}

fun Context.showToast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}