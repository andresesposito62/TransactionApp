package com.transactionapp.transactionauthorization.viewmodel

import androidx.lifecycle.ViewModel
import com.transactionapp.transactionauthorization.usecase.PostTransactionAuthorizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionAuthorizationViewModelImpl @Inject constructor(
    private val postTransactionAuthorizationUseCase: PostTransactionAuthorizationUseCase
): TransactionAuthorizationViewModel, ViewModel() {


}