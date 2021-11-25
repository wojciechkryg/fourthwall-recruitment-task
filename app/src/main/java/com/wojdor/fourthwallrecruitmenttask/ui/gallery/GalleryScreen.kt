package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.wojdor.fourthwallrecruitmenttask.R
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.common.ErrorMessage
import com.wojdor.fourthwallrecruitmenttask.ui.common.Loader
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*
import com.wojdor.fourthwallrecruitmenttask.ui.util.navigateToDetails

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    Gallery(viewModel, state, navController)
}

@ExperimentalFoundationApi
@Composable
fun Gallery(viewModel: GalleryViewModel, state: GalleryState, navController: NavController) {
    when (state) {
        Idle -> viewModel.sendIntent(DownloadPhotos)
        Loading -> Loader()
        is Photos -> PhotoGrid(state.photos) { navController.navigateToDetails(it) }
        is Error -> ErrorMessage(
            title = stringResource(R.string.gallery_error_title),
            message = stringResource(R.string.gallery_error_message)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PhotoGrid(photos: List<Photo>, onClick: (Photo) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(COLUMN_COUNT)) {
        items(photos) { PhotoGridItem(it, onClick) }
    }
}

@Composable
fun PhotoGridItem(photo: Photo, onClick: (Photo) -> Unit) {
    Image(
        painter = rememberImagePainter(photo.imageUrl) {
            crossfade(true)
        },
        contentDescription = stringResource(R.string.photo_grid_item_description, photo.author),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ITEM_ASPECT_RATIO)
            .clickable { onClick(photo) }
    )
}

private const val COLUMN_COUNT = 2
private const val ITEM_ASPECT_RATIO = 1f