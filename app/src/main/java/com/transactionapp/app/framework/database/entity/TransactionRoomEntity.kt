package com.transactionapp.app.framework.database.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "transacions")
data class TransactionRoomEntity (
    @PrimaryKey(autoGenerate = true) @NonNull var id: Long = 0,
    @SerializedName("transactionId")  var transactionId: String? = null,
    @SerializedName("commerceCode") var commerceCode: String? = null,
    @SerializedName("terminalCode") var terminalCode: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("card") var card: String? = null,
    @SerializedName("receiptId") var receiptId: String? = null,
    @SerializedName("rrn") var rrn: String? = null,
    @SerializedName("statusCode") var statusCode: String? = null,
    @SerializedName("statusDescription") var statusDescription: String? = null
)