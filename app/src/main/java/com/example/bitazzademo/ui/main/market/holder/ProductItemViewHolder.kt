package com.example.bitazzademo.ui.main.market.holder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.ItemMarketBinding

class ProductItemViewHolder(var view: ItemMarketBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: ProductListViewType) {
        view.apply {
            if (item is ProductListViewType.Item) {
                tvCryptoName.text = item.product
                tvVolumePrice.text = item.tickSize.toString()
                tvPrice1.text = item.decimalPlaces.toString()
                tvPrice2.text = item.decimalPlaces.toString()
                imgPercent.apply {
                    text = resources.getString(R.string.market_mock_value_change)
                    background = if (item.marginEnabled == false) ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_rectangle_radius_green
                    ) else ContextCompat.getDrawable(context, R.drawable.bg_rectangle_radius_red)
                }
            }
        }
    }
}