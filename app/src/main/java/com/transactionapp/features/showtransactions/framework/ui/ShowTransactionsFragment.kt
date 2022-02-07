package com.transactionapp.features.showtransactions.framework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.transactionapp.R
import com.transactionapp.app.domain.Transaction
import com.transactionapp.databinding.FragmentListTransactionBinding
import com.transactionapp.features.showtransactions.viewmodel.ShowTransactionsViewModelImpl

class ShowTransactionsFragment : Fragment() {

    lateinit var viewModel: ShowTransactionsViewModelImpl

    private var _binding: FragmentListTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var alertDialog:  AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_transaction_app) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = ViewModelProvider(requireActivity())[ShowTransactionsViewModelImpl::class.java]

        viewModel.transactionListErrorLiveData.observe(viewLifecycleOwner) {
            setFailureDialog(it)
        }

        viewModel.transactionListResultLiveData.observe(viewLifecycleOwner) {
            discardLoader()
            if (it.isEmpty()){
                navController.navigate(R.id.noTransactionsAvailableFragment)
            } else{
                setRecyclerView(it)
            }
        }

        _binding = FragmentListTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog?.cancel()
        setLoader()
        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager

        viewModel.onGetTransactionList("")
    }

    private fun setRecyclerView(transactionList: List<Transaction>){
        val adapter = TransactionsAdapter(transactionList)
        binding.recyclerView.adapter = adapter
        adapter.notifyItemInserted(transactionList.size-1)
    }

    private fun setFailureDialog(message: String){
        discardLoader()
        alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.failure_transaction_authorization))
                .setMessage("Error:$message")
                .show()
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

    override fun onResume() {
        super.onResume()
        alertDialog?.cancel()
        viewModel.onGetTransactionList("")
    }
}