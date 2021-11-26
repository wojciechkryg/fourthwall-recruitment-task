package com.wojdor.fourthwallrecruitmenttask.domain.usecase

import android.net.Uri
import com.wojdor.fourthwallrecruitmenttask.data.repository.file.FileRepository
import com.wojdor.fourthwallrecruitmenttask.di.coroutine.IoDispatcher
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.BaseParameterUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotoUriUseCase @Inject constructor(
    private val fileRepository: FileRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseParameterUseCase<String, Uri>(coroutineDispatcher) {

    override fun execute(parameter: String): Flow<Result<Uri>> = flow {
        val bitmap = fileRepository.downloadImage(parameter)
        val uri = bitmap?.let { fileRepository.saveImageToCache(it) }
        uri?.let {
            emit(Result.Success(it))
        } ?: run {
            emit(
                Result.Error(
                    IllegalArgumentException("Cannot download photo from following url: $parameter")
                )
            )
        }
    }
}