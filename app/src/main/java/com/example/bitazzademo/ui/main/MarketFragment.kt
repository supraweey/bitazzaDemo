package com.example.bitazzademo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentMarketBinding

class MarketFragment : Fragment() {
    lateinit var binding: FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketBinding.inflate(inflater)
        return binding.root
    }

}