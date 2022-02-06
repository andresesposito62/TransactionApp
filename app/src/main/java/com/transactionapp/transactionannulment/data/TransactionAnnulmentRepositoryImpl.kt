package com.transactionapp.transactionannulment.data

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse
import com.transactionapp.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import javax.inject.Inject

class TransactionAnnulmentRepositoryImpl @Inject constructor(
    private val transactionAnnulmentRemoteSource: TransactionAnnulmentRemoteSourceImpl
): TransactionAnnulmentRepository {

    override suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?> =
        transactionAnnulmentRemoteSource.postTransactionAuthorization(authorization, transactionAnnulmentBody)
}