package com.transactionapp.app.framework.di

import com.transactionapp.app.framework.database.dao.TransactionDao
import com.transactionapp.features.transactionannulment.data.TransactionAnnulmentRepository
import com.transactionapp.features.transactionannulment.data.TransactionAnnulmentRepositoryImpl
import com.transactionapp.features.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.features.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun transactionAuthorizationRepositoryProvide(
        transactionAuthorizationDataSourceImpl: TransactionAuthorizationRemoteSourceImpl,
        transactionAuthorizationDao: TransactionDao
    ) = TransactionRepositoryImpl(transactionAuthorizationDataSourceImpl,
        transactionAuthorizationDao)

    @Singleton
    @Provides
    fun transactionAnnulmentRepositoryProvide(
        transactionAnnulmentRemoteSourceImpl: TransactionAnnulmentRemoteSourceImpl
    ): TransactionAnnulmentRepository = TransactionAnnulmentRepositoryImpl(transactionAnnulmentRemoteSourceImpl)
}