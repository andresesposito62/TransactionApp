package com.transactionapp.transactionauthorization.usecase

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.data.TransactionAuthorizationRepositoryImpl
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import io.reactivex.Single
import javax.inject.Inject

class PostTransactionAuthorizationUseCaseImpl @Inject constructor(
    private val transactionAuthorizationRepository: TransactionAuthorizationRepositoryImpl
): PostTransactionAuthorizationUseCase {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        authorizationBody: AuthorizationBody
    ): Single<ResultData<AuthorizationResponse?>> {
        return when (
            val result = transactionAuthorizationRepository
                        .postTransactionAuthorization(authorization, authorizationBody)){

            is ResultData.Success<*> -> Single.just(result)
            is ResultData.Failure<*> -> Single.just(result)
        }

    }
}