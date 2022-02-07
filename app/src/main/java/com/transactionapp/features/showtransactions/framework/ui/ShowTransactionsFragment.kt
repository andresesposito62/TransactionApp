package com.transactionapp.features.showtransactions.framework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.transactionapp.app.domain.Transaction
import com.transactionapp.databinding.FragmentListTransactionBinding
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl

class ShowTransactionsFragment : Fragment() {

    lateinit var viewModel: ShowTransactionsViewModelImpl

    private var _binding: FragmentListTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[ShowTransactionsViewModelImpl::class.java]

        viewModel.transactionListErrorLiveData.observe(viewLifecycleOwner) {
            //binding.textView.text = it
        }

        viewModel.transactionListResultLiveData.observe(viewLifecycleOwner) {
            setRecyclerView(it)
        }

        _binding = FragmentListTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager

        viewModel.onGetTransactionList("")
    }

    private fun setRecyclerView(transactionList: List<Transaction>){
        val adapter = TransactionsAdapter(transactionList)
        binding.recyclerView.adapter = adapter
        adapter.notifyItemInserted(transactionList.size-1)
    }
}