package com.example.bitazzademo.ui.main.market.holder

import androidx.annotation.StringRes
import java.math.BigDecimal

sealed class MarketListViewType {
    data class Header(
        @StringRes val volume: Int,
        @StringRes val price: Int,
        @StringRes val change: Int
    ) : MarketListViewType()

    data class Item(
        val omsId: Int? = null,
        val productId: Int? = null,
        val product: String? = null,
        val productFullName: String? = null,
        val masterDataUniqueProductSymbol: String? = null,
        val productType: String? = null,
        val decimalPlaces: Int? = null,
        val tickSize: BigDecimal? = null,
        val depositEnabled: Boolean? = null,
        val withdrawEnabled: Boolean? = null,
        val noFees: Boolean? = null,
        val isDisabled: Boolean? = null,
        val marginEnabled: Boolean? = null
    ) : MarketListViewType()
}