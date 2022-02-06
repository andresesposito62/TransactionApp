package com.transactionapp.app.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.app.domain.Transaction

interface TransactionRepository {

    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?>

    suspend fun storeTransaction(
        transactionAuthorizationBody: TransactionAuthorizationBody,
        authorizationResponse  : AuthorizationResponse
    )

    suspend fun getTransactionsList(receiptId: String): ResultData<List<Transaction?>>
}