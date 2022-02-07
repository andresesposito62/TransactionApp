package com.transactionapp.app.framework.di

import com.transactionapp.features.searchtransaction.viewmodel.SearchTransactionViewModelImpl
import com.transactionapp.features.showtransactions.usecase.GetTransactionsUseCaseImpl
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl
import com.transactionapp.features.transactionauthorization.usecase.PostTransactionAuthorizationUseCaseImpl
import com.transactionapp.features.transactionauthorization.viewmodel.TransactionAuthorizationViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun transactionAuthorizationViewModelProvide(
        transactionAuthorizationUseCaseImpl: PostTransactionAuthorizationUseCaseImpl
    ) = TransactionAuthorizationViewModelImpl(transactionAuthorizationUseCaseImpl)

    @Provides
    fun showTransactionsViewModelProvide(
        getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl,
    ) = ShowTransactionsViewModelImpl(getTransactionsUseCaseImpl)

    @Provides
    fun searchTransactionViewModelProvide(
        getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl
    ) = SearchTransactionViewModelImpl(getTransactionsUseCaseImpl)
}