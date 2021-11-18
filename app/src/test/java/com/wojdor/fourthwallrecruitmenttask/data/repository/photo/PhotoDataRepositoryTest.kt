package com.wojdor.fourthwallrecruitmenttask.data.repository.photo

import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.data.remote.photo.PhotoRemoteSource
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
class PhotoDataRepositoryTest {

    @RelaxedMockK
    private lateinit var photoRemoteSource: PhotoRemoteSource

    @InjectMockKs
    private lateinit var photoDataRepository: PhotoDataRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @Test
    fun `Check that PhotoDataRepository fetch data from PhotoRemoteSource`() = runBlockingTest {
        photoDataRepository.getPhotos()

        coVerifyOnce { photoRemoteSource.getPhotos() }
        confirmVerified(photoRemoteSource)
    }
}