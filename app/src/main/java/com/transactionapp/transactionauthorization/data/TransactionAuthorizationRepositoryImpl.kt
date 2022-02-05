package com.transactionapp.transactionauthorization.data

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSource
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import javax.inject.Inject

class TransactionAuthorizationRepositoryImpl @Inject constructor(
    private val transactionAuthorizationRemoteSource: TransactionAuthorizationRemoteSourceImpl
): TransactionAuthorizationRepository {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        authorizationBody: AuthorizationBody
    ): ResultData<AuthorizationResponse?> = transactionAuthorizationRemoteSource
        .postTransactionAuthorization(authorization, authorizationBody)
}