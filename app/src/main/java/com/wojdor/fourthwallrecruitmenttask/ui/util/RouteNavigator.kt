package com.wojdor.fourthwallrecruitmenttask.ui.util

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsScreen
import com.wojdor.fourthwallrecruitmenttask.ui.details.PHOTO_EXTRA
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryScreen
import com.wojdor.fourthwallrecruitmenttask.ui.util.Route.DETAILS
import com.wojdor.fourthwallrecruitmenttask.ui.util.Route.GALLERY

@ExperimentalFoundationApi
@Composable
fun RouteNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = GALLERY) {
        composable(GALLERY) { GalleryScreen(navController = navController) }
        composable(DETAILS) { DetailsScreen(it) }
    }
}

fun NavController.navigate(route: String, args: Bundle) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()
    graph.matchDeepLink(routeLink)?.let { navigate(it.destination.id, args) }
}

fun NavController.navigateToDetails(photo: Photo) {
    navigate(DETAILS, bundleOf(PHOTO_EXTRA to photo))
}

object Route {
    const val GALLERY = "gallery"
    const val DETAILS = "details"
}
