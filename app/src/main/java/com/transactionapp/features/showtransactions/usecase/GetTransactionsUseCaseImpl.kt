package com.transactionapp.features.showtransactions.usecase

import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction
import io.reactivex.Single

class GetTransactionsUseCaseImpl(
    private val transactionRepositoryImpl: TransactionRepositoryImpl
): GetTransactionsUseCase {

    override suspend fun getTransaction(receiptId: String): Single<ResultData<List<Transaction?>>> {
        return when (
            val result = transactionRepositoryImpl.getTransactionsList(receiptId)){
            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }
    }

}