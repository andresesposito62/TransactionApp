package com.transactionapp.app.framework.restapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TransactionAuthorizationBody (
    @SerializedName("id") var transactionId: String? = null,
    @SerializedName("commerceCode") var commerceCode: String? = null,
    @SerializedName("terminalCode") var terminalCode: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("card") var card: String? = null
): Serializable