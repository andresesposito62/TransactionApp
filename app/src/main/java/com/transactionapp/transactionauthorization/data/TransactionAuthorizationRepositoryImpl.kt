package com.transactionapp.transactionauthorization.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import javax.inject.Inject

class TransactionAuthorizationRepositoryImpl @Inject constructor(
    private val transactionAuthorizationRemoteSource: TransactionAuthorizationRemoteSourceImpl
): TransactionAuthorizationRepository {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?> = transactionAuthorizationRemoteSource
        .postTransactionAuthorization(authorization, transactionAuthorizationBody)
}