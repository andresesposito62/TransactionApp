package com.transactionapp.app.framework

import com.transactionapp.app.framework.database.entity.TransactionRoomEntity
import com.transactionapp.app.domain.Transaction

object Utils {

    fun generateTransactionRoomEntity(transaction: Transaction?): TransactionRoomEntity {
        return TransactionRoomEntity(
            0,
            transaction?.transactionId,
            transaction?.commerceCode,
            transaction?.terminalCode,
            transaction?.amount,
            transaction?.cardNumber,
            transaction?.receiptId,
            transaction?.rrn,
            transaction?.statusCode,
            transaction?.statusDescription
        )
    }

    fun generateTransactionModel(transactionRoomEntity: TransactionRoomEntity?): Transaction {
        return Transaction(
            transactionRoomEntity?.transactionId,
            transactionRoomEntity?.commerceCode,
            transactionRoomEntity?.terminalCode,
            transactionRoomEntity?.amount,
            transactionRoomEntity?.card,
            transactionRoomEntity?.receiptId,
            transactionRoomEntity?.rrn,
            transactionRoomEntity?.statusCode,
            transactionRoomEntity?.statusDescription
        )
    }
}