package com.transactionapp.base.framework.di

import com.transactionapp.transactionauthorization.data.TransactionAuthorizationRepositoryImpl
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
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
        transactionAuthorizationDataSourceImpl: TransactionAuthorizationRemoteSourceImpl
    ) = TransactionAuthorizationRepositoryImpl(transactionAuthorizationDataSourceImpl)
}