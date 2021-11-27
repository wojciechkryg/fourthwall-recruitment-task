package com.wojdor.fourthwallrecruitmenttask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(val id: String = "", val author: String = "", val imageUrl: String = "") :
    Parcelable