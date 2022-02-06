package com.transactionapp.features.transactionannulment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactionapp.app.domain.ResultData
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
class TransactionAnnulmentViewModelImpl @Inject constructor(
    private val postTransactionAnnulmentUseCase: PostTransactionAnnulmentUseCaseImpl
): TransactionAnnulmentViewModel, ViewModel() {

    private var postTransactionAnnulmentJob: Job? = null

    private val transactionAnnulmentError: MutableLiveData<String> = MutableLiveData()
    private val transactionAnnulmentResult: MutableLiveData<AnnulmentResponse> = MutableLiveData()

    val transactionAnnulmentErrorLiveData: LiveData<String> = transactionAnnulmentError
    val transactionAnnulmentResultLiveData: LiveData<AnnulmentResponse> = transactionAnnulmentResult

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