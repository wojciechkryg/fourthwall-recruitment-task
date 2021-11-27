package com.wojdor.fourthwallrecruitmenttask.domain.usecase

import com.wojdor.fourthwallrecruitmenttask.data.repository.photo.PhotoRepository
import com.wojdor.fourthwallrecruitmenttask.di.coroutine.IoDispatcher
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.BaseUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<List<Photo>>(coroutineDispatcher) {

    override fun execute() = flow {
        emit(Result.Success(photoRepository.getPhotos()))
    }
}