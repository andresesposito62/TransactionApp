package com.transactionapp.features.transactionannulment.framework.datasource

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse

interface TransactionAnnulmentRemoteSource {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?>
}