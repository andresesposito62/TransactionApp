package com.transactionapp.app.framework.di

import com.transactionapp.features.transactionannulment.usecase.PostTransactionAnnulmentUseCaseImpl
import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.features.showtransactions.usecase.GetTransactionsUseCaseImpl
import com.transactionapp.features.transactionauthorization.usecase.PostTransactionAuthorizationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun postTransactionAuthenticationUseCaseProvide(
        transactionAuthorizationRepositoryImpl: TransactionRepositoryImpl
    ) = PostTransactionAuthorizationUseCaseImpl(transactionAuthorizationRepositoryImpl)

    @Provides
    fun postTransactionAnnulmentUseCaseImplProvide(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ) = PostTransactionAnnulmentUseCaseImpl(transactionRepositoryImpl)

    @Provides
    fun getTransactionsUseCaseImpl(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ) = GetTransactionsUseCaseImpl(transactionRepositoryImpl)
}