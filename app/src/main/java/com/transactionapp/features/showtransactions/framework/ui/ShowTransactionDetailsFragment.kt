package com.transactionapp.features.showtransactions.framework.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.databinding.FragmentShowTransactionDetailsBinding
import com.transactionapp.features.transactionannulment.viewmodel.TransactionAnnulmentViewModelImpl

class ShowTransactionDetailsFragment : Fragment() {

    private lateinit var viewModel: TransactionAnnulmentViewModelImpl
    private var transactionToShow: Transaction? = null
    private var alertDialog:  AlertDialog? = null
    private var _binding: FragmentShowTransactionDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[TransactionAnnulmentViewModelImpl::class.java]
        transactionToShow = arguments?.getSerializable(KEY) as Transaction

        viewModel.transactionAnnulmentErrorLiveData.observe(viewLifecycleOwner) {
            setFailureDialog(it)
        }

        viewModel.transactionAnnulmentResultLiveData.observe(viewLifecycleOwner) {
            setSuccessDialog()
        }

        _binding = FragmentShowTransactionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transaccionDetails.deleteView.visibility = View.VISIBLE
        alertDialog?.cancel()
        hideKeyBoard()

        binding.transaccionDetails.deleteTransactionButton.setOnClickListener {
            setLoader()
            viewModel.onPostTransactionAnnulment(
                AUTHORIZATION, TransactionAnnulmentBody(transactionToShow?.receiptId, transactionToShow?.rrn))
        }

        binding.transaccionDetails.transacctionId.text = transactionToShow?.transactionId ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.commerceCode.text = transactionToShow?.commerceCode ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.terminalCode.text = transactionToShow?.terminalCode ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.amount.text = transactionToShow?.amount ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.cardNumber.text = transactionToShow?.cardNumber ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.receiptId.text = transactionToShow?.receiptId ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.rrn.text = transactionToShow?.rrn ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.statusCode.text = transactionToShow?.statusCode ?: resources.getString(R.string.not_transactions_found_text)
        binding.transaccionDetails.statusDescription.text = transactionToShow?.statusDescription ?: resources.getString(R.string.not_transactions_found_text)
    }

    private fun hideKeyBoard() {
        val view = activity?.currentFocus
        if (view != null) {
            val input = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setLoader(){
        binding.loaderView.visibility = View.VISIBLE
        binding.viewShowTransactions.alpha = 0.5F
    }

    private fun discardLoader(){
        binding.loaderView.visibility = View.INVISIBLE
        binding.viewShowTransactions.alpha = 1.0F
    }

    private fun setSuccessDialog(){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.transaction_deleted))
                .setMessage(resources.getString(R.string.transaction_has_been_deleted))
                .setPositiveButton("OK") { dialog, which ->
                    findNavController().popBackStack()
                }
                .show()
        }

    }

    private fun setFailureDialog(message: String){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.transaction_not_deleted))
                .setMessage(ERROR + message)
                .show()
        }

        findNavController().navigate(R.id.noTransactionsAvailableFragment)
    }

    override fun onResume() {
        super.onResume()
        alertDialog?.cancel()
        hideKeyBoard()
    }

    companion object{
        const val KEY = "transaction"
        const val AUTHORIZATION = "Basic MDAwMTIzMDAwQUJD"
        const val ERROR = "Error:"
    }
}