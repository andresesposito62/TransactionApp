package com.transactionapp.app.framework

import com.google.gson.annotations.SerializedName
import com.transactionapp.app.framework.database.entity.TransactionRoomEntity
import com.transactionapp.transactionauthorization.domain.Transaction

object Utils {

    fun generateTransactionRoomEntity(transaction: Transaction): TransactionRoomEntity {
        return TransactionRoomEntity(
            0,
            transaction.transactionId,
            transaction.commerceCode,
            transaction.terminalCode,
            transaction.amount,
            transaction.card,
            transaction.receiptId,
            transaction.rrn,
            transaction.statusCode,
            transaction.statusDescription
        )
    }

    fun generateTransactionModel(transactionRoomEntity: TransactionRoomEntity): Transaction {
        return Transaction(
            transactionRoomEntity.transactionId,
            transactionRoomEntity.commerceCode,
            transactionRoomEntity.terminalCode,
            transactionRoomEntity.amount,
            transactionRoomEntity.card,
            transactionRoomEntity.receiptId,
            transactionRoomEntity.rrn,
            transactionRoomEntity.statusCode,
            transactionRoomEntity.statusDescription
        )
    }
}