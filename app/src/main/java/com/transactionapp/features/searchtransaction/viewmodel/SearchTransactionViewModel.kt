package com.transactionapp.features.searchtransaction.viewmodel

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction

interface SearchTransactionViewModel {
    fun onGetTransaction(receiptId: String)
    fun handleTransactionResult(result: ResultData<List<Transaction?>>)
    fun setErrorTransaction(throwable: Throwable)
    fun setTransactionData(value: Transaction?)
}