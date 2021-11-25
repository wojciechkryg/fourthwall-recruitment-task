package com.wojdor.fourthwallrecruitmenttask.ui.details

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.Idle
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.PhotoDetails
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @InjectMockKs
    private lateinit var detailsViewModel: DetailsViewModel

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
        val state = detailsViewModel.uiState.value

        assertTrue(state is Idle)
    }

    @Test
    fun `Check that DownloadPhotos intent publish Loading state`() =
        runBlockingTest {
            val photo = relaxedMockk<Photo>()

            detailsViewModel.sendIntent(DetailsIntent.ShowPhotoDetails(photo))

            val state = detailsViewModel.uiState.value
            assertTrue(state is PhotoDetails)
            assertEquals(photo, (state as PhotoDetails).photo)

        }
}