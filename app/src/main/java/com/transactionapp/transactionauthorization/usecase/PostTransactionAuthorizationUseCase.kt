package com.transactionapp.transactionauthorization.usecase

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import io.reactivex.Single

interface PostTransactionAuthorizationUseCase {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): Single<ResultData<AuthorizationResponse?>>
}