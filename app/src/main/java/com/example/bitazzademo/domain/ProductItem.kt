package com.example.bitazzademo.domain

import android.os.Parcelable
import com.example.bitazzademo.ui.main.market.holder.MarketListViewType
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class ProductItem(
    val omsId: Int?,
    val productId: Int?,
    val product: String?,
    val productFullName: String?,
    val masterDataUniqueProductSymbol: String?,
    val productType: String?,
    val decimalPlaces: Int?,
    val tickSize: BigDecimal?,
    val depositEnabled: Boolean?,
    val withdrawEnabled: Boolean?,
    val noFees: Boolean?,
    val isDisabled: Boolean?
) : Parcelable

fun ProductItem.toProductItemViewType(): MarketListViewType.Item {
    return MarketListViewType.Item(
        omsId = omsId,
        productId = productId,
        product = product,
        productFullName = productFullName,
        masterDataUniqueProductSymbol = masterDataUniqueProductSymbol,
        productType = productType,
        decimalPlaces = decimalPlaces,
        tickSize = tickSize,
        depositEnabled = depositEnabled,
        withdrawEnabled = withdrawEnabled,
        noFees = noFees,
        isDisabled = isDisabled
    )
}