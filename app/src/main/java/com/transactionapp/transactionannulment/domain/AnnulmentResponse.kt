package com.transactionapp.transactionannulment.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnnulmentResponse(
    @SerializedName("statusCode") var statusCode: String? = null,
    @SerializedName("statusDescription") var statusDescription: String? = null
) : Serializable