package com.transactionapp.features.searchtransaction.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.databinding.FragmentListTransactionBinding
import com.transactionapp.databinding.FragmentSearchTransactionBinding
import com.transactionapp.features.searchtransaction.viewmodel.SearchTransactionViewModelImpl
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl
import java.util.*

class SearchTransactionFragment : Fragment() {

    lateinit var viewModel: SearchTransactionViewModelImpl

    private var _binding: FragmentSearchTransactionBinding? = null
    private val binding get() = _binding!!

    private var alertDialog:  AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[SearchTransactionViewModelImpl::class.java]

        viewModel.transactionErrorLiveData.observe(viewLifecycleOwner) {
            setFailureDialog(it)
        }

        viewModel.transactionResultLiveData.observe(viewLifecycleOwner) {
            if (it.transactionId != null){
                setSuccessDialog(it)
            }else{
                setFailureDialog("Lo sentimos, no ha sido posible encontrar la transaccion solicitada" )
            }
        }

        _binding = FragmentSearchTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            val receiptId = binding.receiptIdEditText.editText?.text?.trim().toString() ?: ""
            if (receiptId.isNotEmpty()){
                setLoader()
                viewModel.onGetTransaction(receiptId)
            }
        }
    }

    private fun setLoader(){
        binding.loaderView.visibility = View.VISIBLE
        binding.viewContainerTransactionAuthorization.alpha = 0.5F
    }

    private fun discardLoader(){
        binding.loaderView.visibility = View.INVISIBLE
        binding.viewContainerTransactionAuthorization.alpha = 1.0F
    }

    private fun setSuccessDialog(transaction: Transaction){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.transaction_found_success))
                .setMessage(resources.getString(R.string.details_text))
                .setNegativeButton(resources.getString(R.string.decline_text)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.go_text)) { dialog, which ->
                    val bundle = bundleOf("transaction" to transaction)
                    findNavController().navigate(R.id.showTransactionDetailsFragment, bundle)
                }
                .show()
        }
    }

    private fun setFailureDialog(message: String){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.not_transactions_found_text))
                .setMessage(message)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()

        alertDialog?.cancel()
    }

}