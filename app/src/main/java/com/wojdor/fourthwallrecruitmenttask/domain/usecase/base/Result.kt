package com.wojdor.fourthwallrecruitmenttask.domain.usecase.base

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
}