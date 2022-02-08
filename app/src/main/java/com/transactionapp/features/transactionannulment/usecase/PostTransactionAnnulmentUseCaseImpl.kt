package com.transactionapp.features.transactionannulment.usecase

import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import io.reactivex.Single
import javax.inject.Inject

class PostTransactionAnnulmentUseCaseImpl @Inject constructor(
    private val transactionRepositoryImpl: TransactionRepositoryImpl
): PostTransactionAnnulmentUseCase {

    override suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): Single<ResultData<AnnulmentResponse?>> {
        return when (
            val result = transactionRepositoryImpl
                    .postTransactionAnnulment(authorization, transactionAnnulmentBody)){

            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }
    }
}