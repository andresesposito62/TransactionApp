package com.transactionapp.transactionauthorization.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationRepository {

    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?>

    fun storeTransaction(
        transactionAuthorizationBody: TransactionAuthorizationBody,
        authorizationResponse  : AuthorizationResponse
    )
}