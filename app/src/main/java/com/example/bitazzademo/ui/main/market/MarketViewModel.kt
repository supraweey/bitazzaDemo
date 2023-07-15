package com.example.bitazzademo.ui.main.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitazzademo.R
import com.example.bitazzademo.domain.GetProductListUseCase
import com.example.bitazzademo.domain.OMS_ID
import com.example.bitazzademo.domain.ProductItem
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.domain.toProductItemViewType
import com.example.bitazzademo.ui.main.market.holder.ProductListViewType
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MarketViewModel(
    private val prefs: PreferenceStoragable,
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    private val _productItemList = LiveEvent<List<ProductListViewType>>()
    val productItemList: LiveData<List<ProductListViewType>>
        get() = _productItemList

    private val _isError = LiveEvent<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private val _loading by lazy { LiveEvent<Boolean>() }
    val loading: LiveData<Boolean> by lazy { _loading }

    fun executeGetProduct() {
        val omsId = prefs.getInt(OMS_ID, 1)
        viewModelScope.launch {
            getProductListUseCase.execute(omsId)
                .onStart { showLoading() }
                .catch { _isError.value = true }
                .onCompletion { hideLoading() }
                .collect {
                    onGetProduct(it)
                }
        }
    }

    private fun onGetProduct(productItems: List<ProductItem>) {
        val viewTypeList = mutableListOf<ProductListViewType>()
        viewTypeList.add(
            ProductListViewType.Header(
                R.string.market_title_volume,
                R.string.market_title_price,
                R.string.market_title_change
            )
        )
        productItems.forEach {
            viewTypeList.add(it.toProductItemViewType())
        }
        _productItemList.value = viewTypeList
    }

    private fun showLoading() {
        _loading.value = true
    }

    private fun hideLoading() {
        _loading.value = false
    }

    fun handleLogoutData(logout: () -> Unit, error: () -> Unit) {
        if (prefs.getString(USER_KEY_TOKEN, "").isNotEmpty()) {
            logout.invoke()
        }
        else {
            error.invoke()
        }
    }

    fun clearUserData(){
        prefs.delete(USER_KEY_TOKEN)
        prefs.delete(OMS_ID)
    }
}