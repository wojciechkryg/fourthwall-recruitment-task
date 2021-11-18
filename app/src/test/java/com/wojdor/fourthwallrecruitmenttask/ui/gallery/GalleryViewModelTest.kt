package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.GetPhotosUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GalleryViewModelTest {

    @RelaxedMockK
    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @InjectMockKs
    private lateinit var galleryViewModel: GalleryViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `Check that initial state in GalleryViewModel is Idle`() = runBlockingTest {
        val state = galleryViewModel.uiState.value

        assertTrue(state is Idle)
    }

    @Test
    fun `Check that DownloadPhotos intent publish Loading state`() =
        runBlockingTest {
            galleryViewModel.sendIntent(DownloadPhotos)

            val state = galleryViewModel.uiState.value
            assertTrue(state is Loading)
        }

    @Test
    fun `Check that DownloadPhotos intent publish Photos state on success`() =
        runBlockingTest {
            val photos = relaxedMockk<List<Photo>>()
            coEvery { getPhotosUseCase() } returns flow { emit(Result.Success(photos)) }

            galleryViewModel.sendIntent(DownloadPhotos)

            val state = galleryViewModel.uiState.value
            assertTrue(state is Photos)
            assertEquals(photos, (state as Photos).photos)
        }
}