package com.transactionapp.app.framework.restapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TransactionAnnulmentBody (
    @SerializedName("receiptId") var receiptId: String? = null,
    @SerializedName("rrn") var rrn: String? = null
): Serializable