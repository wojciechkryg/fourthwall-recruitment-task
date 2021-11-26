package com.wojdor.fourthwallrecruitmenttask.ui.details

import android.net.Uri
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiEffect

sealed class DetailsEffect : UiEffect {
    data class SharePhoto(val uri: Uri) : UiEffect
    object ShowError : UiEffect
}