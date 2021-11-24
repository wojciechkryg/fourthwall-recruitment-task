package com.wojdor.fourthwallrecruitmenttask.ui.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryScreen

@ExperimentalFoundationApi
@Composable
fun RouteNavigator(navController: NavHostController) {
    NavHost(navController, startDestination = GALLERY) {
        composable(route = GALLERY) {
            GalleryScreen()
        }
    }
}

const val GALLERY = "gallery"

