package com.transactionapp.features.transactionauthorization.usecase

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import io.reactivex.Single
import javax.inject.Inject

class PostTransactionAuthorizationUseCaseImpl @Inject constructor(
    private val transactionAuthorizationRepository: TransactionRepositoryImpl
): PostTransactionAuthorizationUseCase {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): Single<ResultData<AuthorizationResponse?>> {
        return when (
            val result = transactionAuthorizationRepository
                        .postTransactionAuthorization(authorization, transactionAuthorizationBody)){

            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }
    }
}