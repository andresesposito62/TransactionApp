package com.transactionapp.transactionannulment.usecase

import com.transactionapp.transactionannulment.data.TransactionAnnulmentRepositoryImpl
import javax.inject.Inject

class PostTransactionAnnulmentUseCaseImpl @Inject constructor(
    private val transactionAnnulmentRepository: TransactionAnnulmentRepositoryImpl
): PostTransactionAnnulmentUseCase {
}