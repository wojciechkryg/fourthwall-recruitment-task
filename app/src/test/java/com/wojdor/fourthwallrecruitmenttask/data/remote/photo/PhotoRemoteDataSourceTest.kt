package com.wojdor.fourthwallrecruitmenttask.data.remote.photo

import com.wojdor.fourthwallrecruitmenttask.coVerifyOnce
import com.wojdor.fourthwallrecruitmenttask.data.entity.PhotoEntity
import com.wojdor.fourthwallrecruitmenttask.data.mapper.toPhotos
import com.wojdor.fourthwallrecruitmenttask.data.remote.service.PhotoApiService
import com.wojdor.fourthwallrecruitmenttask.relaxedMockk
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
class PhotoRemoteDataSourceTest {


    @RelaxedMockK
    private lateinit var photoApiService: PhotoApiService

    @InjectMockKs
    private lateinit var photoRemoteDataSource: PhotoRemoteDataSource

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @Test
    fun `Check that PhotoRemoteDataSource fetch data from PhotoApiService and map the results`() =
        runBlockingTest {
            val photoEntities = relaxedMockk<List<PhotoEntity>>()
            coEvery { photoApiService.getPhotos() } returns photoEntities

            photoRemoteDataSource.getPhotos()

            coVerifyOnce {
                photoApiService.getPhotos()
                photoEntities.toPhotos()
            }
            confirmVerified(photoApiService, photoEntities)
        }
}