package com.transactionapp.transactionauthorization.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthorizationResponse(
    @SerializedName("receiptId") var receiptId: String? = null,
    @SerializedName("rrn") var rrn: String? = null,
    @SerializedName("statusCode") var statusCode: String? = null,
    @SerializedName("statusDescription") var statusDescription: String? = null
) : Serializable