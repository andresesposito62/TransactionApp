package com.transactionapp.transactionannulment.viewmodel

import androidx.lifecycle.ViewModel
import com.transactionapp.transactionannulment.usecase.PostTransactionAnnulmentUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionAnnulmentViewModelImpl @Inject constructor(
    private val transactionAnnulmentUseCase: PostTransactionAnnulmentUseCaseImpl
): TransactionAnnulmentViewModel, ViewModel() {
}