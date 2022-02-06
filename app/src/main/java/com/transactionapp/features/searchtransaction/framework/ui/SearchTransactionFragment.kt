package com.transactionapp.features.searchtransaction.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.transactionapp.R
import com.transactionapp.databinding.FragmentListTransactionBinding
import com.transactionapp.databinding.FragmentSearchTransactionBinding
import com.transactionapp.features.searchtransaction.viewmodel.SearchTransactionViewModelImpl
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl

class SearchTransactionFragment : Fragment() {

    lateinit var viewModel: SearchTransactionViewModelImpl

    private var _binding: FragmentSearchTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[SearchTransactionViewModelImpl::class.java]

        viewModel.transactionErrorLiveData.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }

        viewModel.transactionResultLiveData.observe(viewLifecycleOwner) {
            binding.textView.text = it.toString()
        }

        _binding = FragmentSearchTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGetTransaction("b8711bc4-bede-448c-9c9d-9a9ea59420c3")
    }

}