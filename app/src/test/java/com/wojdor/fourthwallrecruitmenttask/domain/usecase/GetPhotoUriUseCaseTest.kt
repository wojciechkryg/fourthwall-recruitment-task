package com.wojdor.fourthwallrecruitmenttask.domain.usecase

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.data.repository.file.FileRepository
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
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetPhotoUriUseCaseTest {

    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    private lateinit var fileRepository: FileRepository

    @InjectMockKs
    private lateinit var getPhotoUriUseCase: GetPhotoUriUseCase

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
    fun `Check that GetPhotoUriUseCase emits success with uri`() = runBlockingTest {
        val imageUrl = "http://example.com"
        val bitmap = relaxedMockk<Bitmap>()
        val uri = relaxedMockk<Uri>()
        with(fileRepository) {
            coEvery { downloadImage(imageUrl) } returns bitmap
            coEvery { saveImageToCache(bitmap) } returns uri
        }

        val result = getPhotoUriUseCase(imageUrl).single()

        coVerifyOnce {
            with(fileRepository) {
                downloadImage(imageUrl)
                saveImageToCache(bitmap)
            }
        }
        confirmVerified(fileRepository)
        assertEquals(uri, (result as Success).data)
    }

    @Test
    fun `Check that GetPhotoUriUseCase throws error on saveImageToCache`() = runBlockingTest {
        val imageUrl = "http://example.com"
        val bitmap = relaxedMockk<Bitmap>()
        val error = RuntimeException()
        with(fileRepository) {
            coEvery { downloadImage(imageUrl) } returns bitmap
            coEvery { saveImageToCache(bitmap) } throws error
        }

        val result = getPhotoUriUseCase(imageUrl).single()

        coVerifyOnce {
            with(fileRepository) {
                downloadImage(imageUrl)
                saveImageToCache(bitmap)
            }
        }
        confirmVerified(fileRepository)
        assertEquals(error, (result as Error).error)
    }

    @Test
    fun `Check that GetPhotoUriUseCase throws error on downloadImage`() = runBlockingTest {
        val imageUrl = "http://example.com"
        val error = RuntimeException()
        coEvery { fileRepository.downloadImage(imageUrl) } throws error

        val result = getPhotoUriUseCase(imageUrl).single()

        coVerifyOnce { fileRepository.downloadImage(imageUrl) }
        confirmVerified(fileRepository)
        assertEquals(error, (result as Error).error)
    }

    @Test
    fun `Check that GetPhotoUriUseCase emits error when bitmap is null`() = runBlockingTest {
        val imageUrl = "http://example.com"
        val bitmap = null
        coEvery { fileRepository.downloadImage(imageUrl) } returns bitmap

        val result = getPhotoUriUseCase(imageUrl).single()

        coVerifyOnce { fileRepository.downloadImage(imageUrl) }
        confirmVerified(fileRepository)
        assertTrue((result as Error).error is IllegalArgumentException)
    }
}