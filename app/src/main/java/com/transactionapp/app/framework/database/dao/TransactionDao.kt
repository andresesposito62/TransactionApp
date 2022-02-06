package com.transactionapp.app.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.transactionapp.app.framework.database.entity.TransactionRoomEntity

@Dao
abstract class TransactionDao {
    @Insert
    abstract fun insert(transactionEntity: TransactionRoomEntity): Long


}