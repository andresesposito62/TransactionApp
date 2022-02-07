package com.transactionapp.features.transactionannulment.framework.datasource

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.ServicesRestApi
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
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
                    if (response.code() in MIN_CODE_SUCCESS..MAX_CODE_SUCCESS){
                        it.resume(ResultData.Success(response.body()!!))
                    }else{
                        it.resume(ResultData.Failure(Exception(response.code().toString())))
                    }
                }

                override fun onFailure(call: Call<AnnulmentResponse>, error: Throwable) {
                    it.resume(ResultData.Failure(error))
                }
            })
        }

    companion object{
        const val MIN_CODE_SUCCESS = 200
        const val MAX_CODE_SUCCESS = 399
    }
}