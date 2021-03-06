package com.transactionapp.features.transactionauthorization.framework.datasource

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.restapi.ServicesRestApi
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TransactionAuthorizationRemoteSourceImpl @Inject constructor(
    private val servicesRestApi: ServicesRestApi
): TransactionAuthorizationRemoteSource {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?> =
        suspendCoroutine {
            val result = servicesRestApi.transactionAuthorizationResponse(authorization, transactionAuthorizationBody)
            result?.enqueue(object: Callback<AuthorizationResponse>{
                override fun onResponse(call: Call<AuthorizationResponse>,
                    response: Response<AuthorizationResponse>
                ) {
                    if (response.code() in MIN_CODE_SUCCESS..MAX_CODE_SUCCESS){
                        it.resume(ResultData.Success(response.body()!!))
                    }else{
                        it.resume(ResultData.Failure(Exception(response.code().toString())))
                    }
                }

                override fun onFailure(call: Call<AuthorizationResponse>, error: Throwable) {
                    it.resume(ResultData.Failure(error))
                }
            })
        }
    companion object{
        const val MIN_CODE_SUCCESS = 200
        const val MAX_CODE_SUCCESS = 399
    }
}