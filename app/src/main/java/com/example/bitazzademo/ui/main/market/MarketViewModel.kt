package com.example.bitazzademo.ui.main.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitazzademo.R
import com.example.bitazzademo.domain.GetProductListUseCase
import com.example.bitazzademo.domain.OMSID
import com.example.bitazzademo.domain.ProductItem
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.domain.toProductItemViewType
import com.example.bitazzademo.ui.main.market.holder.MarketListViewType
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MarketViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val prefs: PreferenceStoragable
) : ViewModel() {
    private val _productItemList = LiveEvent<List<MarketListViewType>>()
    val productItemList: LiveData<List<MarketListViewType>>
        get() = _productItemList

    private val _isError = LiveEvent<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    fun executeGetProduct() {
        val omsId = prefs.getInt(OMSID, 0)
        viewModelScope.launch {
            getProductListUseCase.execute(1)
                .onStart { }
                .catch { _isError.value = true }
                .collect {
                    onGetProduct(it)
                }
        }
    }

    private fun onGetProduct(productItems: List<ProductItem>) {
        val viewTypeList = mutableListOf<MarketListViewType>()
        viewTypeList.add(MarketListViewType.Header(R.string.market_title_volume, R.string.market_title_price, R.string.market_title_change))
        productItems.forEach{
            viewTypeList.add(it.toProductItemViewType())
        }
        _productItemList.value = viewTypeList
    }
}