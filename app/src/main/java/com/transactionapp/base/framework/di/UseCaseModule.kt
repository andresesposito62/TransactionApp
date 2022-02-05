package com.transactionapp.base.framework.di

import com.transactionapp.transactionauthorization.data.TransactionAuthorizationRepositoryImpl
import com.transactionapp.transactionauthorization.usecase.PostTransactionAuthorizationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun postTransactionAuthenticationUseCaseProvide(
        transactionAuthorizationRepositoryImpl: TransactionAuthorizationRepositoryImpl
    ) = PostTransactionAuthorizationUseCaseImpl(transactionAuthorizationRepositoryImpl)

}