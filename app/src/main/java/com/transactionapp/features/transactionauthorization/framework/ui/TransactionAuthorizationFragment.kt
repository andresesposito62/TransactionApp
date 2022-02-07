package com.transactionapp.features.transactionauthorization.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.databinding.FragmentTransactionAuthorizationBinding
import com.transactionapp.features.transactionauthorization.domain.AuthorizationResponse
import com.transactionapp.features.transactionauthorization.viewmodel.TransactionAuthorizationViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TransactionAuthorizationFragment : Fragment() {

    private lateinit var viewModel: TransactionAuthorizationViewModelImpl
    private var alertDialog:  AlertDialog? = null
    private var transaction = Transaction(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )

    private var _binding: FragmentTransactionAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[TransactionAuthorizationViewModelImpl::class.java]

        viewModel.transactionAuthorizationErrorLiveData.observe(viewLifecycleOwner) {
            setFailureDialog(it)
        }

        viewModel.transactionAuthorizationResultLiveData.observe(viewLifecycleOwner) {
            setSuccessDialog(it)
        }

        _binding = FragmentTransactionAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog?.cancel()
        binding.autorizationButtonRequest.setOnClickListener {
            setLoader()
            val uniqueId = UUID.randomUUID().toString()

            transaction.transactionId = uniqueId ?:""
            transaction.commerceCode = binding.commerceCode.editText?.text?.trim().toString() ?: ""
            transaction.terminalCode = binding.terminalCode.editText?.text?.trim().toString() ?: ""
            transaction.amount = binding.amount.editText?.text?.trim().toString() ?: ""
            transaction.cardNumber = binding.cardNumber.editText?.text?.trim().toString() ?: ""

            val authorizationBody = TransactionAuthorizationBody(
                transaction.transactionId ,
                transaction.commerceCode,
                transaction.terminalCode,
                transaction.amount ,
                transaction.cardNumber)

            viewModel.onPostTransactionAuthorization(AUTHORIZATION, authorizationBody)
        }
    }

    private fun setLoader(){
        binding.loaderView.visibility = View.VISIBLE
        binding.viewContainerTransactionAuthorization.alpha = ALPHA_CONSTANT_HALF
    }

    private fun discardLoader(){
        binding.loaderView.visibility = View.INVISIBLE
        binding.viewContainerTransactionAuthorization.alpha = ALPHA_CONSTANT
    }

    private fun setSuccessDialog(authorizationResponse: AuthorizationResponse){
        discardLoader()
        alertDialog =context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.success_transaction_authorization))
                .setMessage(resources.getString(R.string.details_text))
                .setNegativeButton(resources.getString(R.string.decline_text)) { dialog, which ->
                    binding.commerceCode.editText?.text?.clear()
                    binding.terminalCode.editText?.text?.clear()
                    binding.amount.editText?.text?.clear()
                    binding.cardNumber.editText?.text?.clear()
                    binding.cardNumber.editText?.clearFocus()
                }
                .setPositiveButton(resources.getString(R.string.go_text)) { dialog, which ->
                    transaction.receiptId = authorizationResponse.receiptId ?: ""
                    transaction.rrn = authorizationResponse.rrn ?: ""
                    transaction.statusCode = authorizationResponse.statusCode ?: ""
                    transaction.statusDescription = authorizationResponse.statusDescription ?: ""

                    val bundle = bundleOf(TRANSACTION to transaction)
                    findNavController().navigate(R.id.showTransactionDetailsFragment, bundle)
                }
                .show()
        }
    }

    private fun setFailureDialog(message: String){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.failure_transaction_authorization))
                .setMessage(ERROR + message)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        alertDialog?.cancel()
    }

    companion object{
        const val TRANSACTION = "transaction"
        const val ERROR = "Error:"
        const val AUTHORIZATION = "Basic MDAwMTIzMDAwQUJD"
        const val ALPHA_CONSTANT_HALF = 0.5F
        const val ALPHA_CONSTANT = 1.0F
    }
}