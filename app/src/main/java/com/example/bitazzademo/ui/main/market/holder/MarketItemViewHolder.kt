package com.example.bitazzademo.ui.main.market.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.bitazzademo.databinding.ItemMarketBinding
import com.example.bitazzademo.domain.ProductItem

class MarketItemViewHolder(var view: ItemMarketBinding): RecyclerView.ViewHolder(view.root) {

    fun bind(item: MarketListViewType){
        view.apply {
            if (item is MarketListViewType.Item){
                tvCryptoName.text = item.product
                tvVolumePrice.text = item.tickSize.toString()
            }
        }
    }
}