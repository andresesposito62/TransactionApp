package com.transactionapp.transactionauthorization.framework.datasource

import com.transactionapp.base.domain.ResultData
import com.transactionapp.base.framework.restapi.ServicesRestApi
import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TransactionAuthorizationRemoteSourceImpl@Inject constructor(
    private val servicesRestApi: ServicesRestApi
): TransactionAuthorizationRemoteSource {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        authorizationBody: AuthorizationBody
    ): ResultData<AuthorizationResponse> =
        suspendCoroutine {
            val result = servicesRestApi.authorizationResponse(authorization, authorizationBody)
            result?.enqueue(object: Callback<AuthorizationResponse>{
                override fun onResponse(call: Call<AuthorizationResponse>,
                    response: Response<AuthorizationResponse>
                ) {
                    it.resume(ResultData.Success(response.body()!!))
                }

                override fun onFailure(call: Call<AuthorizationResponse>, error: Throwable) {
                    it.resume(ResultData.Failure(error))
                }

            })
        }


}