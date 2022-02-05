package com.transactionapp.transactionauthorization.data

import com.transactionapp.base.framework.restapi.ServicesRestApi
import javax.inject.Inject

class TransactionAuthorizationRepositoryImpl @Inject constructor(
    val servicesRestApi: ServicesRestApi
): TransactionAuthorizationRepository {
}