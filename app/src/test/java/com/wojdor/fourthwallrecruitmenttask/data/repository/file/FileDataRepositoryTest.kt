package com.wojdor.fourthwallrecruitmenttask.data.repository.file

import android.graphics.Bitmap
import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.data.source.local.file.FileLocalSource
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.image.ImageRemoteSource
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class FileDataRepositoryTest {

    @RelaxedMockK
    private lateinit var imageRemoteSource: ImageRemoteSource

    @RelaxedMockK
    private lateinit var fileLocalSource: FileLocalSource

    @InjectMockKs
    private lateinit var fileDataRepository: FileDataRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @Test
    fun `Check that FileDataRepository can download image`() = runBlockingTest {
        val imageUrl = "http://example.com"

        fileDataRepository.downloadImage(imageUrl)

        coVerifyOnce { imageRemoteSource.downloadImage(imageUrl) }
        confirmVerified(imageRemoteSource)
    }

    @Test
    fun `Check that FileDataRepository can save bitmap to file`() = runBlockingTest {
        val bitmap = relaxedMockk<Bitmap>()

        fileDataRepository.saveImageToCache(bitmap)

        coVerifyOnce { fileLocalSource.saveBitmapToFile(bitmap) }
        confirmVerified(fileLocalSource)
    }
}