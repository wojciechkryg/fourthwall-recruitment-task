package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import coil.compose.rememberImagePainter
import com.wojdor.fourthwallrecruitmenttask.R
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.common.ErrorMessage
import com.wojdor.fourthwallrecruitmenttask.ui.common.Loader
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    Gallery(viewModel, state)
}

@ExperimentalFoundationApi
@Composable
fun Gallery(viewModel: GalleryViewModel, state: GalleryState) {
    when (state) {
        Idle -> viewModel.sendIntent(GalleryIntent.DownloadPhotos)
        Loading -> Loader()
        is Photos -> PhotoGrid(state.photos)
        is Error -> ErrorMessage(
            title = stringResource(R.string.gallery_error_title),
            message = stringResource(R.string.gallery_error_message)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PhotoGrid(photos: List<Photo>) {
    LazyVerticalGrid(GridCells.Fixed(COLUMN_COUNT)) {
        items(photos) { PhotoGridItem(it) }
    }
}

@Composable
fun PhotoGridItem(photo: Photo) {
    Image(
        painter = rememberImagePainter(photo.imageUrl) {
            crossfade(true)
        },
        contentDescription = stringResource(R.string.photo_grid_item_description, photo.author),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ITEM_ASPECT_RATIO)
    )
}

private const val COLUMN_COUNT = 2
private const val ITEM_ASPECT_RATIO = 1f