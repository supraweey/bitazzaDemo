package com.example.bitazzademo.data

import com.example.bitazzademo.domain.ProductItem
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class GetProductResponse(
    @SerializedName("OMSId") val omsId: Int?,
    @SerializedName("ProductId") val productId: Int?,
    @SerializedName("Product") val product: String?,
    @SerializedName("ProductFullName") val productFullName: String?,
    @SerializedName("MasterDataUniqueProductSymbol") val masterDataUniqueProductSymbol: String?,
    @SerializedName("ProductType") val productType: String?,
    @SerializedName("DecimalPlaces") val decimalPlaces: Int?,
    @SerializedName("TickSize") val tickSize: BigDecimal?,
    @SerializedName("DepositEnabled") val depositEnabled: Boolean?,
    @SerializedName("WithdrawEnabled") val withdrawEnabled: Boolean?,
    @SerializedName("NoFees") val noFees: Boolean?,
    @SerializedName("IsDisabled") val isDisabled: Boolean?,
    @SerializedName("MarginEnabled") val marginEnabled: Boolean?
)

fun GetProductResponse.mapToDomain(): ProductItem =
    ProductItem(
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
        isDisabled = isDisabled,
        marginEnabled = marginEnabled
    )