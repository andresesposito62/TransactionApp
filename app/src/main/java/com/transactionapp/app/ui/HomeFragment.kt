package com.transactionapp.app.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.transactionapp.R
import com.transactionapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Long


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object : CountDownTimer(animationTime, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                binding.containerHomeView.alpha = millisUntilFinished * (factor/animationTime)
            }

            override fun onFinish() {
                if (findNavController().currentDestination?.id == R.id.homeFragment){
                    findNavController().navigate(R.id.listTransactionFragment)
                }
            }
        }.start()
    }

    companion object{
        const val animationTime = 3000L
        const val  countDownInterval = 1L
        const val factor = 1F
    }
}