package com.transactionapp.base.framework.di

import com.transactionapp.transactionannulment.data.TransactionAnnulmentRepository
import com.transactionapp.transactionannulment.data.TransactionAnnulmentRepositoryImpl
import com.transactionapp.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import com.transactionapp.transactionauthorization.data.TransactionAuthorizationRepositoryImpl
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun transactionAuthorizationRepositoryProvide(
        transactionAuthorizationDataSourceImpl: TransactionAuthorizationRemoteSourceImpl
    ) = TransactionAuthorizationRepositoryImpl(transactionAuthorizationDataSourceImpl)

    @Singleton
    @Provides
    fun transactionAnnulmentRepositoryProvide(
        transactionAnnulmentRemoteSourceImpl: TransactionAnnulmentRemoteSourceImpl
    ): TransactionAnnulmentRepository = TransactionAnnulmentRepositoryImpl(transactionAnnulmentRemoteSourceImpl)
}