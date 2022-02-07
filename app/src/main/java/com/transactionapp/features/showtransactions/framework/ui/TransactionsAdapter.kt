package com.transactionapp.features.showtransactions.framework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction

class TransactionsAdapter(private val dataSet: List<Transaction>) : RecyclerView.Adapter<TransactionsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TransactionsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_details_card, viewGroup, false)

        return TransactionsViewHolder(view)
    }

    override fun onBindViewHolder(transactionsViewHolder: TransactionsViewHolder, position: Int) {
        transactionsViewHolder.transactionId.text = dataSet[position].transactionId ?: ""
        transactionsViewHolder.commerceCode.text = dataSet[position].commerceCode ?: ""
        transactionsViewHolder.terminalCode.text = dataSet[position].terminalCode ?: ""
        transactionsViewHolder.amount.text = dataSet[position].amount ?: ""
        transactionsViewHolder.cardNumber.text = dataSet[position].cardNumber ?: ""
        transactionsViewHolder.receiptId.text = dataSet[position].receiptId ?: ""
        transactionsViewHolder.rrn.text = dataSet[position] .rrn?: ""
        transactionsViewHolder.statusCode.text = dataSet[position].statusCode ?: ""
        transactionsViewHolder.statusDescription.text = dataSet[position].statusDescription ?: ""
    }

    override fun getItemCount() = dataSet.size
}

class TransactionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val transactionId: TextView = view.findViewById(R.id.transacctionId)
    val commerceCode: TextView = view.findViewById(R.id.commerceCode)
    val terminalCode: TextView = view.findViewById(R.id.terminalCode)
    val amount: TextView = view.findViewById(R.id.amount)
    val cardNumber: TextView = view.findViewById(R.id.cardNumber)
    val receiptId: TextView = view.findViewById(R.id.receiptId)
    val rrn: TextView = view.findViewById(R.id.rrn)
    val statusCode: TextView = view.findViewById(R.id.statusCode)
    val statusDescription: TextView = view.findViewById(R.id.statusDescription)
}

