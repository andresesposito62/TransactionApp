package com.transactionapp.transactionannulment.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.transactionapp.R
import com.transactionapp.base.framework.restapi.model.TransactionAnnulmentBody
import com.transactionapp.base.framework.restapi.model.TransactionAuthorizationBody
import com.transactionapp.databinding.FragmentTransactionAnnulmentBinding
import com.transactionapp.databinding.FragmentTransactionAuthorizationBinding
import com.transactionapp.transactionannulment.viewmodel.TransactionAnnulmentViewModelImpl
import com.transactionapp.transactionauthorization.viewmodel.TransactionAuthorizationViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TransactionAnnulmentFragment : Fragment() {

    lateinit var viewModel: TransactionAnnulmentViewModelImpl

    private var _binding: FragmentTransactionAnnulmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[TransactionAnnulmentViewModelImpl::class.java]

        viewModel.transactionAnnulmentErrorLiveData.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }

        viewModel.transactionAnnulmentResultLiveData.observe(viewLifecycleOwner) {
            binding.textView.text = it.toString()
        }

        _binding = FragmentTransactionAnnulmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authorization = "Basic MDAwMTIzMDAwQUJD"
        val annulmentBody = TransactionAnnulmentBody("4612671f-566a-42a7-b640-f32defe90275", "0aa35a53-1acd-465b-bf76-503b25b5e374")

        viewModel.onPostTransactionAnnulment(authorization, annulmentBody)
    }
}