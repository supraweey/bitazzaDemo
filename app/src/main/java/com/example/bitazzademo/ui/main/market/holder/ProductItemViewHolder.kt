package com.example.bitazzademo.ui.main.market.holder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.ItemMarketBinding

class ProductItemViewHolder(var view: ItemMarketBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: ProductListViewType, position: Int) {
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
                mockUpLogo(itemView.context, position, item)
            }
        }
    }

    private fun mockUpLogo(
        context: Context,
        position: Int,
        item: ProductListViewType.Item
    ) {
        if (position == 1) {
            Glide.with(context)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/800px-Bitcoin.svg.png")
                .placeholder(R.drawable.bg_oval_gray)
                .circleCrop()
                .error(R.drawable.bg_oval_gray)
                .into(view.imgCrypto)
        } else {
            Glide.with(context)
                .load(item.masterDataUniqueProductSymbol)
                .placeholder(R.drawable.bg_oval_gray)
                .circleCrop()
                .error(R.drawable.bg_oval_gray)
                .into(view.imgCrypto)
        }
    }
}