package com.transactionapp.showtransactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactionapp.app.domain.ResultData
import com.transactionapp.showtransactions.usecase.GetTransactionsUseCaseImpl
import com.transactionapp.transactionauthorization.domain.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.functions.Consumer

@HiltViewModel
class ShowTransactionsViewModelImpl @Inject constructor(
    private val getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl
): ShowTransactionsViewModel, ViewModel() {

    private var getTransactionListJob: Job? = null

    private val transactionListError: MutableLiveData<String> = MutableLiveData()
    private val transactionListResult: MutableLiveData<List<Transaction>> = MutableLiveData()

    val transactionListErrorLiveData: LiveData<String> = transactionListError
    val transactionListResultLiveData: LiveData<List<Transaction>> = transactionListResult

    override fun onGetTransactionList() {
        getTransactionListJob?.cancel()
        getTransactionListJob = viewModelScope.launch {
            withContext(Dispatchers.IO){
                getTransactionsUseCaseImpl.getTransactionList().subscribe(Consumer {
                        handleTransactionListResult(it)
                    })
            }
        }
    }

    override fun handleTransactionListResult(result: ResultData<List<Transaction?>>) {
        when(result){
            is ResultData.Success -> setTransactionListData(result.value)
            is ResultData.Failure -> setErrorTransactionList(result.throwable)
        }
    }

    override fun setErrorTransactionList(throwable: Throwable) {
        transactionListError.postValue(throwable.stackTraceToString())
    }

    override fun setTransactionListData(value: List<Transaction?>) {
        if (!value.isNullOrEmpty()){
            transactionListResult.postValue(value as List<Transaction>?)
        }
    }
}