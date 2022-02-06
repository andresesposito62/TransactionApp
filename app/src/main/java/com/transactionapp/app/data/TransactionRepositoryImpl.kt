package com.transactionapp.app.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.Utils
import com.transactionapp.app.framework.Utils.generateTransactionModel
import com.transactionapp.app.framework.database.dao.TransactionDao
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.app.domain.Transaction
import com.transactionapp.features.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import javax.inject.Inject
import kotlin.contracts.Returns

class TransactionRepositoryImpl @Inject constructor(
    private val transactionAuthorizationRemoteSource: TransactionAuthorizationRemoteSourceImpl,
    private val transactionDao: TransactionDao
): TransactionRepository {

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

     override suspend fun storeTransaction(
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
        transactionDao.insert(roomEntity)

    }

    override suspend fun getTransactionsList(receiptId: String): ResultData<List<Transaction?>> {

        return if (receiptId == ""){
            val result =  transactionDao.getTransactionList().map {
                generateTransactionModel(it)
            }
            ResultData.Success(result)
        }else{
            val result = listOf(generateTransactionModel(transactionDao.getTransactionByReceiptId(receiptId)))
            ResultData.Success(result)
        }
    }

}