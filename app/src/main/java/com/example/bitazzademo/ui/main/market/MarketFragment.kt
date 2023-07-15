package com.example.bitazzademo.ui.main.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.FragmentMarketBinding
import com.example.bitazzademo.ui.account.AccountActivity
import com.example.bitazzademo.ui.main.MainActivity
import com.example.bitazzademo.ui.main.market.adapter.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarketFragment : Fragment() {
    private lateinit var binding: FragmentMarketBinding
    private val viewModel by viewModel<MarketViewModel>()
    private var productAdapter: ProductAdapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.executeGetProduct()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onObserve()
    }

    override fun onResume() {
        super.onResume()
        handleActionBar()
    }

    private fun setUpRecyclerView() {
        binding.rvProductList.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onObserve() {
        viewModel.productItemList.observe(this) {
            productAdapter.updateItem(it)
        }
        viewModel.loading.observe(requireActivity()) {
            binding.progressBar.isVisible = it
        }
        viewModel.isError.observe(requireActivity()) {
            Toast.makeText(requireContext(), "Product error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleActionBar() {
        (requireActivity() as MainActivity).apply {
            setCustomActionBar(
                requireContext(),
                title = getString(R.string.app_name),
                labelEnd = getString(R.string.label_button_logout)
            ) {
                handleLogoutClick()
            }
        }
    }

    private fun handleLogoutClick() {
        viewModel.apply {
            handleLogoutData(
                logout = {
                    clearUserData()
                    AccountActivity.startActivity(requireContext())
                },
                error = {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.message_logout_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}