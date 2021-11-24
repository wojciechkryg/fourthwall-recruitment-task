package com.wojdor.fourthwallrecruitmenttask.ui.app

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.wojdor.fourthwallrecruitmenttask.ui.util.RouteNavigator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()

@ExperimentalFoundationApi
@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    RouteNavigator(navController)
}