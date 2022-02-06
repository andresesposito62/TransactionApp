package com.transactionapp.app.framework.di

import com.transactionapp.transactionannulment.data.TransactionAnnulmentRepositoryImpl
import com.transactionapp.transactionannulment.usecase.PostTransactionAnnulmentUseCaseImpl
import com.transactionapp.app.data.TransactionRepositoryImpl
import com.transactionapp.showtransactions.usecase.GetTransactionsUseCaseImpl
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
        transactionAuthorizationRepositoryImpl: TransactionRepositoryImpl
    ) = PostTransactionAuthorizationUseCaseImpl(transactionAuthorizationRepositoryImpl)

    @Provides
    fun postTransactionAnnulmentUseCaseImplProvide(
        transactionAnnulmentRepositoryImpl: TransactionAnnulmentRepositoryImpl
    ) = PostTransactionAnnulmentUseCaseImpl(transactionAnnulmentRepositoryImpl)

    @Provides
    fun getTransactionsUseCaseImpl(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ) = GetTransactionsUseCaseImpl(transactionRepositoryImpl)
}