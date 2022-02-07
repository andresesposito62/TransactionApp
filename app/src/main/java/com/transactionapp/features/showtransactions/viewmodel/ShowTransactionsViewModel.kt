package com.transactionapp.features.showtransactions.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse

interface ShowTransactionsViewModel {
    fun onGetTransactionList(receiptId: String)
    fun handleTransactionListResult(result: ResultData<List<Transaction?>>)
    fun setErrorTransactionList(throwable: Throwable)
    fun setTransactionListData(value: List<Transaction?>)
    fun onPostTransactionAnnulment(authorization: String, transactionAnnulmentBody: TransactionAnnulmentBody)
    fun handleTransactionAnnulmentResult(result: ResultData<AnnulmentResponse?>)
    fun setErrorTransactionAnnulment(throwable: Throwable)
    fun setTransactionAnnulmentData(value: AnnulmentResponse?)
}