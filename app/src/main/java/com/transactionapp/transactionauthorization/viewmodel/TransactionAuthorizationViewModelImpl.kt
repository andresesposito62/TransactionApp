package com.transactionapp.transactionauthorization.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.transactionauthorization.usecase.PostTransactionAuthorizationUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionAuthorizationViewModelImpl @Inject constructor(
    private val postTransactionAuthorizationUseCase: PostTransactionAuthorizationUseCaseImpl
): TransactionAuthorizationViewModel, ViewModel() {

    private var postTransactionAuthorizationJob: Job? = null

    private val transactionAuthorizationError: MutableLiveData<String> = MutableLiveData()
    private val transactionAuthorizationResult: MutableLiveData<AuthorizationResponse> = MutableLiveData()

    val transactionAuthorizationErrorLiveData:LiveData<String> = transactionAuthorizationError
    val transactionAuthorizationResultLiveData:LiveData<AuthorizationResponse> = transactionAuthorizationResult

    override fun onPostTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ) {
        postTransactionAuthorizationJob?.cancel()
        postTransactionAuthorizationJob = viewModelScope.launch {
            withContext(Dispatchers.IO){
                postTransactionAuthorizationUseCase
                    .postTransactionAuthorization(authorization, transactionAuthorizationBody)
                        .subscribe(Consumer {
                                handleTransactionAuthorizationResult(it)
                        })
            }
        }
    }

    override fun handleTransactionAuthorizationResult(result: ResultData<AuthorizationResponse?>) {
        when(result){
            is ResultData.Success -> setTransactionAuthorizationData(result.value)
            is ResultData.Failure -> setErrorTransactionAuthorization(result.throwable)
        }
    }

    override fun setErrorTransactionAuthorization(throwable: Throwable) {
        transactionAuthorizationError.postValue(throwable.stackTraceToString())
    }

    override fun setTransactionAuthorizationData(value: AuthorizationResponse?) {
        if (value != null){
            transactionAuthorizationResult.postValue(value)
        }
    }
}