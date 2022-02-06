package com.transactionapp.showtransactions.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.Transaction

interface ShowTransactionsViewModel {
    fun onGetTransactionList()
    fun handleTransactionListResult(result: ResultData<List<Transaction?>>)
    fun setErrorTransactionList(throwable: Throwable)
    fun setTransactionListData(value: List<Transaction?>)
}