package com.example.bitazzademo.ui.main.wallet.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentWalletFaitBinding

class WalletFaitFragment : Fragment() {
    lateinit var binding: FragmentWalletFaitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletFaitBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = WalletFaitFragment()
    }

}