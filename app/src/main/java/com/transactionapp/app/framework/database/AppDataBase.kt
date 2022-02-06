package com.transactionapp.app.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.transactionapp.app.framework.database.dao.TransactionDao
import com.transactionapp.app.framework.database.entity.TransactionRoomEntity

@Database(
    entities = [
        TransactionRoomEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getTransactionDao(): TransactionDao

    companion object{
        private var INSTANCE: AppDataBase? = null
        private const val DATABASE_NAME = "localDataBase.db"

        /*private val MIGRATION = object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database
                    .execSQL("CREATE TABLE IF NOT EXISTS `Transaction` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"                            " `commerceCode` TEXT)")


            }

        }*/

        fun getInstance(context: Context): AppDataBase{
            if (INSTANCE == null){
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        //.addMigrations(MIGRATION)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}