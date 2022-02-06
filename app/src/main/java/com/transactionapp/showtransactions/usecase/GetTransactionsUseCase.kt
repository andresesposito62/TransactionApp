package com.transactionapp.showtransactions.usecase

import com.transactionapp.app.domain.ResultData
import com.transactionapp.transactionauthorization.domain.Transaction
import io.reactivex.Single

interface GetTransactionsUseCase {
    suspend fun getTransactionList(): Single<ResultData<List<Transaction?>>>
}