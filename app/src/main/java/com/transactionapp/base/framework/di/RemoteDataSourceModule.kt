package com.transactionapp.base.framework.di

import com.transactionapp.base.framework.restapi.ServicesEndPoints
import com.transactionapp.base.framework.restapi.ServicesRestApi
import com.transactionapp.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSource
import com.transactionapp.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun retrofitProvide(): Retrofit
        = Retrofit.Builder()
        .baseUrl(ServicesEndPoints.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun serviceRestApiProvide(retrofit: Retrofit): ServicesRestApi
        = retrofit.create(ServicesRestApi::class.java)

    @Singleton
    @Provides
    fun transactionAuthorizationDataSourceProvide(
        servicesRestApi: ServicesRestApi
    ) = TransactionAuthorizationRemoteSourceImpl(servicesRestApi)

    @Singleton
    @Provides
    fun transactionAnnulmentRemoteSourceProvide(
        servicesRestApi: ServicesRestApi
    ) = TransactionAnnulmentRemoteSourceImpl(servicesRestApi)
}