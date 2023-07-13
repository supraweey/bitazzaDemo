package com.example.bitazzademo.ui.main.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentMarketBinding
import com.example.bitazzademo.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    lateinit var binding: FragmentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletBinding.inflate(inflater)
        return binding.root
    }

}