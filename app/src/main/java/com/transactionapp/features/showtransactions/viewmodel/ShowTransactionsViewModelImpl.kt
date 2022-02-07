package com.transactionapp.features.showtransactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactionapp.app.domain.ResultData
import com.transactionapp.features.showtransactions.usecase.GetTransactionsUseCaseImpl
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import com.transactionapp.features.transactionannulment.usecase.PostTransactionAnnulmentUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.functions.Consumer

@HiltViewModel
class ShowTransactionsViewModelImpl @Inject constructor(
    private val getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl,
    private val postTransactionAnnulmentUseCase: PostTransactionAnnulmentUseCaseImpl
): ShowTransactionsViewModel, ViewModel() {

    private var getTransactionListJob: Job? = null
    private var postTransactionAnnulmentJob: Job? = null

    private val transactionListError: MutableLiveData<String> = MutableLiveData()
    private val transactionListResult: MutableLiveData<List<Transaction>> = MutableLiveData()
    private val transactionAnnulmentError: MutableLiveData<String> = MutableLiveData()
    private val transactionAnnulmentResult: MutableLiveData<AnnulmentResponse> = MutableLiveData()

    val transactionListErrorLiveData: LiveData<String> = transactionListError
    val transactionListResultLiveData: LiveData<List<Transaction>> = transactionListResult
    val transactionAnnulmentErrorLiveData: LiveData<String> = transactionAnnulmentError
    val transactionAnnulmentResultLiveData: LiveData<AnnulmentResponse> = transactionAnnulmentResult

    override fun onGetTransactionList(receiptId: String) {
        getTransactionListJob?.cancel()
        getTransactionListJob = viewModelScope.launch {
            withContext(Dispatchers.IO){
                getTransactionsUseCaseImpl.getTransaction(receiptId).subscribe(Consumer {
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
        transactionListResult.postValue(value as List<Transaction>?)
    }

    override fun onPostTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ) {
        postTransactionAnnulmentJob?.cancel()
        postTransactionAnnulmentJob = viewModelScope.launch {
            withContext(Dispatchers.IO){
                postTransactionAnnulmentUseCase
                    .postTransactionAnnulment(authorization, transactionAnnulmentBody)
                    .subscribe(Consumer {
                        handleTransactionAnnulmentResult(it)
                    })
            }
        }
    }

    override fun handleTransactionAnnulmentResult(result: ResultData<AnnulmentResponse?>) {
        when(result){
            is ResultData.Success -> setTransactionAnnulmentData(result.value)
            is ResultData.Failure -> setErrorTransactionAnnulment(result.throwable)
        }
    }

    override fun setErrorTransactionAnnulment(throwable: Throwable) {
        transactionAnnulmentError.postValue(throwable.stackTraceToString())
    }

    override fun setTransactionAnnulmentData(value: AnnulmentResponse?) {
        if (value != null){
            transactionAnnulmentResult.postValue(value)
        }
    }
}