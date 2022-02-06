package com.transactionapp.features.searchtransaction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.domain.Transaction
import com.transactionapp.features.showtransactions.usecase.GetTransactionsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.functions.Consumer

@HiltViewModel
class SearchTransactionViewModelImpl @Inject constructor(
    private val getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl
): SearchTransactionViewModel, ViewModel() {

    private var getTransactionJob: Job? = null

    private val transactionError: MutableLiveData<String> = MutableLiveData()
    private val transactionResult: MutableLiveData<Transaction> = MutableLiveData()

    val transactionErrorLiveData: LiveData<String> = transactionError
    val transactionResultLiveData: LiveData<Transaction> = transactionResult

    override fun onGetTransaction(receiptId: String) {
        getTransactionJob?.cancel()
        getTransactionJob = viewModelScope.launch {
            withContext(Dispatchers.IO){
                getTransactionsUseCaseImpl.getTransaction(receiptId).subscribe(Consumer {
                    handleTransactionResult(it)
                })
            }
        }
    }

    override fun handleTransactionResult(result: ResultData<List<Transaction?>>) {
        when(result){
            is ResultData.Success -> setTransactionData(result.value[0])
            is ResultData.Failure -> setErrorTransaction(result.throwable)
        }
    }

    override fun setErrorTransaction(throwable: Throwable) {
        transactionError.postValue(throwable.stackTraceToString())
    }

    override fun setTransactionData(value:Transaction?) {
        if (value != null){
            transactionResult.postValue(value)
        }
    }
}