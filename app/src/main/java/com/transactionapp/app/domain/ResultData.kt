package com.transactionapp.app.domain

sealed class ResultData<T>{
    data class Success<T>(val value:T): ResultData<T>()
    data class Failure<T>(val throwable: Throwable): ResultData<T>()
}
