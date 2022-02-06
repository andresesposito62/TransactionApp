package com.transactionapp.transactionannulment.usecase

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse
import io.reactivex.Single

interface PostTransactionAnnulmentUseCase {
    suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): Single<ResultData<AnnulmentResponse?>>
}