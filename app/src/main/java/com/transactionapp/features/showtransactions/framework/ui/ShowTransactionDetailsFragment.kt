package com.transactionapp.features.showtransactions.framework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.databinding.FragmentShowTransactionDetailsBinding
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl


class ShowTransactionDetailsFragment : Fragment() {

    private var _binding: FragmentShowTransactionDetailsBinding? = null
    private val binding get() = _binding!!

    var transactionToShow: Transaction? = null

    lateinit var viewModel: ShowTransactionsViewModelImpl

    private var alertDialog:  AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[ShowTransactionsViewModelImpl::class.java]
        transactionToShow = arguments?.getSerializable("transaction") as Transaction

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

        binding.transaccionDetails.deleteTransactionButton.setOnClickListener {
            setLoader()
            val authorization = "Basic MDAwMTIzMDAwQUJD"
            viewModel.onPostTransactionAnnulment(authorization, TransactionAnnulmentBody(transactionToShow?.receiptId, transactionToShow?.rrn))
        }

        binding.transaccionDetails.transacctionId.text =
            transactionToShow?.transactionId ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.commerceCode.text =
            transactionToShow?.commerceCode ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.terminalCode.text =
            transactionToShow?.terminalCode ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.amount.text =
            transactionToShow?.amount ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.cardNumber.text =
            transactionToShow?.cardNumber ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.receiptId.text =
            transactionToShow?.receiptId ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.rrn.text =
            transactionToShow?.rrn ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.statusCode.text =
            transactionToShow?.statusCode ?: resources.getString(R.string.not_transactions_found_text)

        binding.transaccionDetails.statusDescription.text =
            transactionToShow?.statusDescription ?: resources.getString(R.string.not_transactions_found_text)
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
                .setTitle("Transacción eliminada!")
                .setMessage("La transacción ya no se encuentra en el sistema")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.cancel()
                    findNavController().popBackStack()
                }
                .show()
        }

    }

    private fun setFailureDialog(message: String){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Lo sentimos...No da sido posible eliminar la transacción")
                .setMessage("Error:$message")
                .show()
        }
        findNavController().navigate(R.id.noTransactionsAvailableFragment)
    }

    override fun onResume() {
        super.onResume()

        alertDialog?.cancel()
    }

}