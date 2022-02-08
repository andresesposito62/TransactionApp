package com.transactionapp.features.transactionauthorization.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationViewModel {
    fun onPostTransactionAuthorization(authorization: String, transactionAuthorizationBody: TransactionAuthorizationBody)
    fun handleTransactionAuthorizationResult(result: ResultData<AuthorizationResponse?>)
    fun setErrorTransactionAuthorization(throwable: Throwable)
    fun setTransactionAuthorizationData(value: AuthorizationResponse?)
}