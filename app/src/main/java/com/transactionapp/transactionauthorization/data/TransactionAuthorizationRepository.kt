package com.transactionapp.transactionauthorization.data

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationRepository {

    suspend fun postTransactionAuthorization(
        authorization: String,
        authorizationBody: AuthorizationBody
    ): ResultData<AuthorizationResponse?>
}