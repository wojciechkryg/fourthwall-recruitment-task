package com.wojdor.fourthwallrecruitmenttask.ui.details

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowPhotoDetails
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
    fun `Check that initial state in GalleryViewModel is PhotoDetails with empty Photo`() =
        runBlockingTest {
            val state = detailsViewModel.uiState.value

            assertTrue(state is PhotoDetails)
            assertEquals(Photo(), (state as PhotoDetails).photo)
        }

    @Test
    fun `Check that ShowPhotoDetails intent publish PhotoDetails state`() =
        runBlockingTest {
            val photo = relaxedMockk<Photo>()

            detailsViewModel.sendIntent(ShowPhotoDetails(photo))

            val state = detailsViewModel.uiState.value
            assertTrue(state is PhotoDetails)
            assertEquals(photo, (state as PhotoDetails).photo)

        }
}