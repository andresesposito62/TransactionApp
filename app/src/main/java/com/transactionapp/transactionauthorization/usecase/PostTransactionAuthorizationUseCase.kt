package com.transactionapp.transactionauthorization.usecase

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import io.reactivex.Single

interface PostTransactionAuthorizationUseCase {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): Single<ResultData<AuthorizationResponse?>>
}