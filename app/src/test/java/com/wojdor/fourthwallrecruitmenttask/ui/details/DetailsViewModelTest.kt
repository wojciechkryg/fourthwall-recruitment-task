package com.wojdor.fourthwallrecruitmenttask.ui.details

import android.net.Uri
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.GetPhotoUriUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsEffect.SharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsEffect.ShowError
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowSharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.PhotoDetails
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class DetailsViewModelTest {

    @RelaxedMockK
    private lateinit var getPhotoUriUseCase: GetPhotoUriUseCase

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
    fun `Check that ShowPhotoDetails intent publish PhotoDetails state`() = runBlockingTest {
        val photo = relaxedMockk<Photo>()

        detailsViewModel.sendIntent(ShowPhotoDetails(photo))

        val state = detailsViewModel.uiState.value
        assertTrue(state is PhotoDetails)
        assertEquals(photo, (state as PhotoDetails).photo)
    }

    @Test
    fun `Check that ShowSharePhoto intent send SharePhoto effect on success`() =
        runBlockingTest {
            val imageUrl = "http://example.com"
            val uri = relaxedMockk<Uri>()
            every { getPhotoUriUseCase(imageUrl) } returns flow { emit(Result.Success(uri)) }

            detailsViewModel.sendIntent(ShowSharePhoto(imageUrl))

            val effect = detailsViewModel.uiEffect.first()
            assertTrue(effect is SharePhoto)
            assertEquals(uri, (effect as SharePhoto).uri)
        }

    @Test
    fun `Check that ShowSharePhoto intent send ShowError effect on error`() =
        runBlockingTest {
            val imageUrl = "http://example.com"
            every { getPhotoUriUseCase(imageUrl) } returns flow {
                emit(Result.Error(RuntimeException()))
            }

            detailsViewModel.sendIntent(ShowSharePhoto(imageUrl))

            val effect = detailsViewModel.uiEffect.first()
            assertTrue(effect is ShowError)
        }
}