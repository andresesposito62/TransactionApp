package com.transactionapp.transactionannulment.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse

interface TransactionAnnulmentRepository {
    suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?>
}