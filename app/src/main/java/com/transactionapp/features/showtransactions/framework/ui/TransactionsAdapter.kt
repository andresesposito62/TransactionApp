package com.transactionapp.features.showtransactions.framework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction

class TransactionsAdapter(private val dataSet: List<Transaction>, private val itemClickListener: OnTransactionClickListener) : RecyclerView.Adapter<TransacionsViewHolder>() {

    interface OnTransactionClickListener{
        fun onItemClick(transaction: Transaction, itemPosition: Int)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TransacionsViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_details_card, viewGroup, false)

        return TransacionsViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(transacionsViewHolder: TransacionsViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        transacionsViewHolder.transactionId.text = "Id de transacción:  \n" + dataSet[position].transactionId ?: ""
        transacionsViewHolder.commerceCode.text = "Código de comercio: \n" + dataSet[position].commerceCode ?: ""
        transacionsViewHolder.terminalCode.text = "Código de terminal: \n" + dataSet[position].terminalCode ?: ""
        transacionsViewHolder.amount.text = "Monto: \n" + dataSet[position].amount ?: ""
        transacionsViewHolder.cardNumber.text = "Número de tarjeta: \n" + dataSet[position].cardNumber ?: ""
        transacionsViewHolder.receiptId.text = "Id de recibo: \n" + dataSet[position].receiptId ?: ""
        transacionsViewHolder.rrn.text = "Rrn: \n" + dataSet[position] .rrn?: ""
        transacionsViewHolder.statusCode.text = "Código de Status: \n" + dataSet[position].statusCode ?: ""
        transacionsViewHolder.statusDescription.text = "Descripción de estatus: \n" + dataSet[position].statusDescription ?: ""

        transacionsViewHolder.deleteTransactionButton.setOnClickListener {
            itemClickListener.onItemClick(dataSet[position], position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
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
    val deleteTransactionButton: TextView = view.findViewById(R.id.deleteTransaction)
}

