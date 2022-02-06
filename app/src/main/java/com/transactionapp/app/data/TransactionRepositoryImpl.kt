package com.transactionapp.app.data

import com.transactionapp.app.domain.ResultData
import com.transactionapp.app.framework.Utils
import com.transactionapp.app.framework.Utils.generateTransactionModel
import com.transactionapp.app.framework.database.dao.TransactionDao
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.features.transactionannulment.domain.AnnulmentResponse
import com.transactionapp.features.transactionannulment.framework.datasource.TransactionAnnulmentRemoteSourceImpl
import com.transactionapp.features.transactionauthorization.framework.datasource.TransactionAuthorizationRemoteSourceImpl
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionAuthorizationRemoteSource: TransactionAuthorizationRemoteSourceImpl,
    private val transactionDao: TransactionDao,
    private val transactionAnnulmentRemoteSource: TransactionAnnulmentRemoteSourceImpl
): TransactionRepository {

    override suspend fun postTransactionAuthorization(
        authorization: String,
        transactionAuthorizationBody: TransactionAuthorizationBody
    ): ResultData<AuthorizationResponse?> {

        val result = transactionAuthorizationRemoteSource
                .postTransactionAuthorization(authorization, transactionAuthorizationBody)

        when (result){
            is ResultData.Success<*> -> storeTransactionInDatabase(transactionAuthorizationBody, result.value as AuthorizationResponse)
        }

        return result
    }

     override suspend fun storeTransactionInDatabase(
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

    override suspend fun postTransactionAnnulment(
        authorization: String,
        transactionAnnulmentBody: TransactionAnnulmentBody
    ): ResultData<AnnulmentResponse?> {

        val result = transactionAnnulmentRemoteSource.postTransactionAuthorization(authorization, transactionAnnulmentBody)

        when (result){
            is ResultData.Success<*> -> transactionAnnulmentBody.receiptId?.let { deleteTransactionOfDatabase(it) }
        }

        return result
    }

    override suspend fun deleteTransactionOfDatabase(receiptId: String){
        val transactionFound = transactionDao.getTransactionByReceiptId(receiptId)
        if (transactionFound?.receiptId != null){
            transactionDao.deleteByReceiptId(transactionFound.receiptId!!)
        }
    }

}