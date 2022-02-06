package com.transactionapp.transactionauthorization.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.database.Utils
import com.transactionapp.app.framework.database.dao.TransactionDao
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.transactionauthorization.domain.Transaction
import com.transactionapp.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import io.reactivex.Single
import javax.inject.Inject

class TransactionAuthorizationRepositoryImpl @Inject constructor(
    private val transactionAuthorizationRemoteSource: TransactionAuthorizationRemoteSourceImpl,
    private val transactionAuthorizationDao: TransactionDao
): TransactionAuthorizationRepository {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?> {

        val result = transactionAuthorizationRemoteSource
                .postTransactionAuthorization(authorization, transactionAuthorizationBody)

        when (result){
            is ResultData.Success<*> -> storeTransaction(transactionAuthorizationBody, result.value as AuthorizationResponse)
        }

        return result
    }

    override fun storeTransaction(
        transactionAuthorizationBody: TransactionAuthorizationBody,
        authorizationResponse  : AuthorizationResponse
    ){

        val transaction = Transaction(
            transactionAuthorizationBody.transactionId,
            transactionAuthorizationBody.commerceCode,
            transactionAuthorizationBody.terminalCode,
            transactionAuthorizationBody.amount,
            transactionAuthorizationBody.card,
            authorizationResponse.receiptId,
            authorizationResponse.rrn,
            authorizationResponse.statusCode,
            authorizationResponse.statusDescription,
        )

        val roomEntity = Utils.generateTransactionRoomEntity(transaction)
        transactionAuthorizationDao.insert(roomEntity)

    }
}