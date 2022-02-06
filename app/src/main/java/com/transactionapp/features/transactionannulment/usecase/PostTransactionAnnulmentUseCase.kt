package com.transactionapp.features.transactionannulment.usecase

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import io.reactivex.Single

interface PostTransactionAnnulmentUseCase {
    suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): Single<ResultData<AnnulmentResponse?>>
}