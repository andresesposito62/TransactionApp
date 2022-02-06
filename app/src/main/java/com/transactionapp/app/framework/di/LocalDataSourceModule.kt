package com.transactionapp.app.framework.di

import android.content.Context
import com.transactionapp.app.framework.database.AppDataBase
import com.transactionapp.app.framework.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun dataBaseProvider(@ApplicationContext context: Context): AppDataBase{
        return AppDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun transactionDaoProvider(dataBase: AppDataBase): TransactionDao{
        return dataBase.getTransactionDao()
    }


}