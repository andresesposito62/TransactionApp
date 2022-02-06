package com.transactionapp.transactionauthorization.framework.datasource

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationRemoteSource {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?>
}