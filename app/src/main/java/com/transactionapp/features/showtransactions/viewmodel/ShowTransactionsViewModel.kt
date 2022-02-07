package com.transactionapp.features.showtransactions.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction

interface ShowTransactionsViewModel {
    fun onGetTransactionList(receiptId: String)
    fun handleTransactionListResult(result: ResultData<List<Transaction?>>)
    fun setErrorTransactionList(throwable: Throwable)
    fun setTransactionListData(value: List<Transaction?>)
}