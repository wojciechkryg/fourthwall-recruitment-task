package com.wojdor.fourthwallrecruitmenttask.ui.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isPortrait() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

@Composable
fun isTablet(): Boolean {
    val screen = LocalConfiguration.current.screenLayout.and(Configuration.SCREENLAYOUT_SIZE_MASK)
    return screen >= Configuration.SCREENLAYOUT_SIZE_XLARGE
}

@Composable
fun getColumnCount(): Int {
    // There is an gradle build issue when trying to do logical operator on composable functions
    val isTablet = isTablet()
    val isPortrait = isPortrait()
    return when {
        isTablet && isPortrait -> TABLET_PORTRAIT_COLUMN_COUNT
        isTablet -> TABLET_LANDSCAPE_COLUMN_COUNT
        isPortrait -> PORTRAIT_COLUMN_COUNT
        else -> LANDSCAPE_COLUMN_COUNT
    }
}

private const val PORTRAIT_COLUMN_COUNT = 2
private const val LANDSCAPE_COLUMN_COUNT = 4
private const val TABLET_PORTRAIT_COLUMN_COUNT = 3
private const val TABLET_LANDSCAPE_COLUMN_COUNT = 5