package com.transactionapp.features.showtransactions.usecase

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction
import io.reactivex.Single

interface GetTransactionsUseCase {
    suspend fun getTransaction(receiptId: String): Single<ResultData<List<Transaction?>>>
}