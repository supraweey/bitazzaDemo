@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.bitazzademo.ui.main.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.FragmentWalletBinding
import com.example.bitazzademo.ui.main.wallet.pager.WalletPagerAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

class WalletFragment : Fragment() {
    lateinit var binding: FragmentWalletBinding

    private val viewPagerAdapter: WalletPagerAdapter by lazy {
        WalletPagerAdapter(
            childFragmentManager, lifecycle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnTapFiat.selectedTabFiat()
        binding.btnTapFiat.run {
            setOnClickListener {
                setStyleBtnActive()
                binding.btnTapCrypto.setStyleBtnDeActive()
                binding.vpWallet.currentItem = 0
            }
        }
        binding.btnTapCrypto.run {
            setOnClickListener {
                setStyleBtnActive()
                binding.btnTapFiat.setStyleBtnDeActive()
                binding.vpWallet.currentItem = 1
            }
        }

        binding.vpWallet.run {
            isUserInputEnabled = false
            adapter = viewPagerAdapter
        }
    }

    private fun AppCompatButton.selectedTabFiat() {
        binding.btnTapCrypto.setStyleBtnDeActive()
        setStyleBtnActive()
        binding.vpWallet.currentItem = 0
    }

    private fun AppCompatButton.setStyleBtnActive() {
        isSelected = true
        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorBlack4C4C4C))
    }

    private fun AppCompatButton.setStyleBtnDeActive() {
        isSelected = false
        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorBlack191919))
    }
}