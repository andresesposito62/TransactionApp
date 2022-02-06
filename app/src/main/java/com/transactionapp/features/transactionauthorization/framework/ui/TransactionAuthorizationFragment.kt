package com.transactionapp.features.transactionauthorization.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
            binding.textView.text = it
        }

        viewModel.transactionAuthorizationResultLiveData.observe(viewLifecycleOwner) {
            binding.textView.text = it.toString()
        }

        _binding = FragmentTransactionAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authorization = "Basic MDAwMTIzMDAwQUJD"
        val uniqueId = UUID.randomUUID().toString()
        val authorizationBody = TransactionAuthorizationBody(uniqueId, "000123", "000ABC", "12345", "1234567890123456")

        viewModel.onPostTransactionAuthorization(authorization, authorizationBody)

    }
}