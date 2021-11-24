package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.activity.viewModels
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
import coil.compose.rememberImagePainter
import com.wojdor.fourthwallrecruitmenttask.R
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.BaseActivity
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*
import com.wojdor.fourthwallrecruitmenttask.ui.util.extension.logD
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class GalleryActivity : BaseActivity<GalleryViewModel>() {

    override val viewModel: GalleryViewModel by viewModels()

    @Composable
    override fun Content() {
        val state by viewModel.uiState.collectAsState()
        GalleryScreen(viewModel, state)
    }
}

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(viewModel: GalleryViewModel, state: GalleryState) {
    when (state) {
        Idle -> viewModel.sendIntent(DownloadPhotos)
        Loading -> viewModel.logD("Loading")
        is Photos -> PhotoGrid(state.photos)
        is Error -> viewModel.logD("Error")
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