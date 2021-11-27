package com.wojdor.fourthwallrecruitmenttask.domain.usecase

import android.util.Log
import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.data.repository.photo.PhotoRepository
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result.Error
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result.Success
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetPhotosUseCaseTest {

    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    private lateinit var photoRepository: PhotoRepository

    @InjectMockKs
    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @Test
    fun `Check that GetPhotosUseCase emits success with photos`() = runBlockingTest {
        val photos = relaxedMockk<List<Photo>>()
        coEvery { photoRepository.getPhotos() } returns photos

        val result = getPhotosUseCase().single()

        coVerifyOnce { photoRepository.getPhotos() }
        confirmVerified(photoRepository)
        assertEquals(photos, (result as Success).data)
    }

    @Test
    fun `Check that GetPhotosUseCase emits error`() = runBlockingTest {
        val error = RuntimeException()
        coEvery { photoRepository.getPhotos() } throws error

        val result = getPhotosUseCase().single()

        coVerifyOnce { photoRepository.getPhotos() }
        confirmVerified(photoRepository)
        assertEquals(error, (result as Error).error)
    }
}