package com.transactionapp.app.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.transactionapp.app.framework.database.entity.TransactionRoomEntity

@Dao
abstract class TransactionDao {
    @Insert
    abstract fun insert(transactionEntity: TransactionRoomEntity): Long

    @Query("SELECT * FROM transacions")
    abstract fun getTransactionList(): List<TransactionRoomEntity>

    @Query("SELECT * FROM transacions WHERE receiptId = :receiptId")
    abstract fun getTransactionByReceiptId(receiptId: String): TransactionRoomEntity
}