package com.transactionapp.transactionannulment.data

import com.transactionapp.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import javax.inject.Inject

class TransactionAnnulmentRepositoryImpl @Inject constructor(
    private val transactionAnnulmentRemoteSource: TransactionAnnulmentRemoteSourceImpl
): TransactionAnnulmentRepository {
}