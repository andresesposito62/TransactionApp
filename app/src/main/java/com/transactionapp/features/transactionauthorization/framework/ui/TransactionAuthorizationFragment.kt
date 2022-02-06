package com.transactionapp.features.transactionauthorization.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.databinding.FragmentTransactionAuthorizationBinding
import com.transactionapp.features.transactionauthorization.viewmodel.TransactionAuthorizationViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TransactionAuthorizationFragment : Fragment() {

    lateinit var viewModel: TransactionAuthorizationViewModelImpl

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
            setSuccessDialog()
        }

        _binding = FragmentTransactionAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.autorizationButton.setOnClickListener {
            setLoader()
            val authorization = "Basic MDAwMTIzMDAwQUJD"
            val uniqueId = UUID.randomUUID().toString()
            val authorizationBody = TransactionAuthorizationBody(
                uniqueId,
                binding.commerceCode.editText?.text?.trim().toString() ?: "",
                binding.terminalCode.editText?.text?.trim().toString() ?: "",
                binding.amount.editText?.text?.trim().toString() ?: "",
                binding.cardNumber.editText?.text?.trim().toString() ?: "")
            viewModel.onPostTransactionAuthorization(authorization, authorizationBody)
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


    private fun setSuccessDialog(){
        discardLoader()
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.success_transaction_authorization))
                .setMessage(resources.getString(R.string.details_text))
                .setNegativeButton(resources.getString(R.string.decline_text)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.go_text)) { dialog, which ->
                    // Respond to positive button press
                }
                .show()
        }
    }

    private fun setFailureDialog(message: String){
        discardLoader()
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.failure_transaction_authorization))
                .setMessage("Error:$message")
                .show()
        }
    }
}