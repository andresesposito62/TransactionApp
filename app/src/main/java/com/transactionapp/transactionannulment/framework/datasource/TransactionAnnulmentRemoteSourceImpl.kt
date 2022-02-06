package com.transactionapp.transactionannulment.framework.datasource

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.ServicesRestApi
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionannulment.domain.AnnulmentResponse
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TransactionAnnulmentRemoteSourceImpl @Inject constructor(
    private val servicesRestApi: ServicesRestApi
): TransactionAnnulmentRemoteSource {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?> =
        suspendCoroutine {
            val result = servicesRestApi.transactionAnnulmentResponse(authorization, transactionAnnulmentBody)
            result?.enqueue(object: Callback<AnnulmentResponse>{
                override fun onResponse(
                    call: Call<AnnulmentResponse>,
                    response: Response<AnnulmentResponse>
                ) {
                    it.resume(ResultData.Success(response.body()!!))
                }

                override fun onFailure(call: Call<AnnulmentResponse>, error: Throwable) {
                    it.resume(ResultData.Failure(error))
                }

            })
        }
}