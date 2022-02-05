package com.transactionapp.transactionauthorization.viewmodel

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationViewModel {
    fun onPostTransactionAuthorization(authorization: String, authorizationBody: AuthorizationBody)
    fun handleTransactionAuthorizationResult(result: ResultData<AuthorizationResponse?>)
    fun setErrorTransactionAuthorization(throwable: Throwable)
    fun setTransactionAuthorizationData(value: AuthorizationResponse?)
}