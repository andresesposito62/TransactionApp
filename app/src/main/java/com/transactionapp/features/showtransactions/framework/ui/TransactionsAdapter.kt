package com.transactionapp.features.showtransactions.framework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction

class TransactionsAdapter(private val dataSet: List<Transaction>) : RecyclerView.Adapter<TransacionsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TransacionsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_details_card, viewGroup, false)

        return TransacionsViewHolder(view)
    }

    override fun onBindViewHolder(transacionsViewHolder: TransacionsViewHolder, position: Int) {

        transacionsViewHolder.transactionId.text = dataSet[position].transactionId ?: ""
        transacionsViewHolder.commerceCode.text = dataSet[position].commerceCode ?: ""
        transacionsViewHolder.terminalCode.text = dataSet[position].terminalCode ?: ""
        transacionsViewHolder.amount.text = dataSet[position].amount ?: ""
        transacionsViewHolder.cardNumber.text = dataSet[position].cardNumber ?: ""
        transacionsViewHolder.receiptId.text = dataSet[position].receiptId ?: ""
        transacionsViewHolder.rrn.text = dataSet[position] .rrn?: ""
        transacionsViewHolder.statusCode.text = dataSet[position].statusCode ?: ""
        transacionsViewHolder.statusDescription.text = dataSet[position].statusDescription ?: ""
    }

    override fun getItemCount() = dataSet.size

}

class TransacionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

