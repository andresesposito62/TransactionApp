package com.transactionapp.transactionannulment.framework.datasource

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse

interface TransactionAnnulmentRemoteSource {
    suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?>
}