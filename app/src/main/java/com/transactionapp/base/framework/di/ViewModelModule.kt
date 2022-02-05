package com.transactionapp.base.framework.di

import com.transactionapp.transactionauthorization.usecase.PostTransactionAuthorizationUseCaseImpl
import com.transactionapp.transactionauthorization.viewmodel.TransactionAuthorizationViewModelImpl
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

}