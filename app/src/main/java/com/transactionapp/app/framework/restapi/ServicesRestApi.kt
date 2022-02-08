package com.transactionapp.app.framework.restapi

import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import retrofit2.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ServicesRestApi {

    @POST(ServicesEndPoints.POST_AUTHORIZATION)
    fun transactionAuthorizationResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Body transactionAuthorizationBody: TransactionAuthorizationBody
    ): Call<AuthorizationResponse>?

    @POST(ServicesEndPoints.POST_ANNULMENT)
    fun transactionAnnulmentResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Body transactionAnnulmentBody: TransactionAnnulmentBody
    ): Call<AnnulmentResponse>?

    companion object{
        const val AUTHORIZATION = "Authorization"
    }
}