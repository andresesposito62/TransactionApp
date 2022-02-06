package com.transactionapp.app.domain

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Transaction (
    @SerializedName("transactionId") var transactionId: String? = null,
    @SerializedName("commerceCode") var commerceCode: String? = null,
    @SerializedName("terminalCode") var terminalCode: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("card") var card: String? = null,
    @SerializedName("receiptId") var receiptId: String? = null,
    @SerializedName("rrn") var rrn: String? = null,
    @SerializedName("statusCode") var statusCode: String? = null,
    @SerializedName("statusDescription") var statusDescription: String? = null
) : Serializable