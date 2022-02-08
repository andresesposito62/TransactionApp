package com.transactionapp.features.transactionauthorization.framework.datasource

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationRemoteSource {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?>
}