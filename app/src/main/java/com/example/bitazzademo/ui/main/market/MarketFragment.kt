package com.example.bitazzademo.ui.main.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.FragmentMarketBinding
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.ui.main.MainActivity
import com.example.bitazzademo.ui.main.market.adapter.MarketAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MarketFragment : Fragment() {
    private val prefs: PreferenceStoragable by inject()
    lateinit var binding: FragmentMarketBinding
    private val viewModel by viewModel<MarketViewModel>()

    private var productAdapter: MarketAdapter = MarketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).apply {
            setCustomActionBar(
                requireContext(),
                title = getString(R.string.app_name),
                labelEnd = getString(R.string.label_button_logout)
            ) {
                handleLoginClick()
            }
        }
        setUpRecyclerView()
        viewModel.executeGetProduct()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
    }

    private fun handleLoginClick() {
        if (prefs.getString(USER_KEY_TOKEN, "").isNotEmpty()) {
            prefs.delete(USER_KEY_TOKEN)
        } else {
            Timber.d("User doesn't login.")
        }
    }

    private fun setUpRecyclerView() {
        binding.rvProductList.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observe(){
        viewModel.productItemList.observe(this) {
            productAdapter.updateItem(it)
        }
    }

}