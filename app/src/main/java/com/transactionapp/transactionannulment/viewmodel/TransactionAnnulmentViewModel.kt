package com.transactionapp.transactionannulment.viewmodel

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse

interface TransactionAnnulmentViewModel {
    fun onPostTransactionAnnulment(authorization: String, transactionAnnulmentBody: TransactionAnnulmentBody)
    fun handleTransactionAnnulmentResult(result: ResultData<AnnulmentResponse?>)
    fun setErrorTransactionAnnulment(throwable: Throwable)
    fun setTransactionAnnulmentData(value: AnnulmentResponse?)
}