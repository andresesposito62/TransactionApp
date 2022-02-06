package com.transactionapp.transactionauthorization.data

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationRepository {

    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?>
}