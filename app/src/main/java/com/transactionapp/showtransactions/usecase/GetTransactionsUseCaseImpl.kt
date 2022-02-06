package com.transactionapp.showtransactions.usecase

import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.app.domain.ResultData
import com.transactionapp.transactionauthorization.domain.Transaction
import io.reactivex.Single

class GetTransactionsUseCaseImpl(
    private val transactionRepositoryImpl: TransactionRepositoryImpl
): GetTransactionsUseCase {

    override suspend fun getTransactionList(): Single<ResultData<List<Transaction?>>> {
        return when (
            val result = transactionRepositoryImpl.getTransactionsList()){
            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }
    }

}