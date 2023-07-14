package com.example.bitazzademo.ui.main.wallet.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class WalletPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = MAX_PAGE

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> WalletFaitFragment.newInstance()
        1 -> WalletCryptoFragment.newInstance()
        else -> Fragment()
    }

    companion object {
        const val MAX_PAGE = 2
    }
}