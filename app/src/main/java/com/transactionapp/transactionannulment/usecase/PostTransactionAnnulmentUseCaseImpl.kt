package com.transactionapp.transactionannulment.usecase

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.data.TransactionAnnulmentRepositoryImpl
import com.transactionapp.transactionannulment.domain.AnnulmentResponse
import io.reactivex.Single
import javax.inject.Inject

class PostTransactionAnnulmentUseCaseImpl @Inject constructor(
    private val transactionAnnulmentRepository: TransactionAnnulmentRepositoryImpl
): PostTransactionAnnulmentUseCase {

    override suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): Single<ResultData<AnnulmentResponse?>> {
        return when (
            val result = transactionAnnulmentRepository
                    .postTransactionAnnulment(authorization, transactionAnnulmentBody)){

            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }
    }
}