package com.wojdor.fourthwallrecruitmenttask.data.source.remote.image

import coil.ImageLoader
import coil.request.ImageRequest
import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class ImageRemoteDataSourceTest {

    @RelaxedMockK
    private lateinit var imageLoader: ImageLoader

    @RelaxedMockK
    private lateinit var imageRequestBuilder: ImageRequest.Builder

    @InjectMockKs
    private lateinit var imageRemoteDataSource: ImageRemoteDataSource

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @Test
    fun `Check that ImageRemoteDataSource can download image`() = runBlockingTest {
        val imageUrl = "http://example.com"
        val request = relaxedMockk<ImageRequest>()
        every { imageRequestBuilder.data(imageUrl) } returns imageRequestBuilder
        every { imageRequestBuilder.build() } returns request

        imageRemoteDataSource.downloadImage(imageUrl)

        coVerifyOnce {
            with(imageRequestBuilder) {
                data(imageUrl)
                build()
            }
            imageLoader.execute(request)
        }
        confirmVerified(imageRequestBuilder, imageLoader)
    }
}