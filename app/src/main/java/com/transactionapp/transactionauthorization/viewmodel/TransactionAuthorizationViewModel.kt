package com.transactionapp.transactionauthorization.viewmodel

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse

interface TransactionAuthorizationViewModel {
    fun onPostTransactionAuthorization(authorization: String, transactionAuthorizationBody: TransactionAuthorizationBody)
    fun handleTransactionAuthorizationResult(result: ResultData<AuthorizationResponse?>)
    fun setErrorTransactionAuthorization(throwable: Throwable)
    fun setTransactionAuthorizationData(value: AuthorizationResponse?)
}