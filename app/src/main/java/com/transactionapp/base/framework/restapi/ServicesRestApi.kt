package com.transactionapp.base.framework.restapi

import com.transactionapp.base.framework.restapi.model.AuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import retrofit2.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ServicesRestApi {

    @POST(ServicesEndPoints.POST_AUTHORIZATION)
    fun authorizationResponse(
        @Header("Authorization") authorization: String,
        @Body authorizationBody: AuthorizationBody
    ): Call<AuthorizationResponse>?
}