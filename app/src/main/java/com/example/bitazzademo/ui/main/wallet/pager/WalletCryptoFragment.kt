package com.example.bitazzademo.ui.main.wallet.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentWalletCryptoBinding

class WalletCryptoFragment : Fragment() {
    lateinit var binding: FragmentWalletCryptoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletCryptoBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = WalletCryptoFragment()
    }

}