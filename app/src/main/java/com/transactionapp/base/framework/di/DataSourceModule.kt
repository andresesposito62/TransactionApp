package com.transactionapp.base.framework.di

import com.transactionapp.base.framework.restapi.ServicesRestApi
import com.transactionapp.transactionauthorization.data.TransactionAuthorizationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun transactionAuthorizationDataSourceProvide(servicesRestApi: ServicesRestApi)
        = TransactionAuthorizationRepositoryImpl(servicesRestApi)

}