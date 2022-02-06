package com.transactionapp.transactionannulment.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse

interface TransactionAnnulmentViewModel {
    fun onPostTransactionAnnulment(authorization: String, transactionAnnulmentBody: TransactionAnnulmentBody)
    fun handleTransactionAnnulmentResult(result: ResultData<AnnulmentResponse?>)
    fun setErrorTransactionAnnulment(throwable: Throwable)
    fun setTransactionAnnulmentData(value: AnnulmentResponse?)
}