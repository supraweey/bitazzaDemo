package com.example.bitazzademo.ui.main.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitazzademo.databinding.ItemHeaderMarketBinding
import com.example.bitazzademo.databinding.ItemMarketBinding
import com.example.bitazzademo.ui.main.market.holder.MarketHeaderViewHolder
import com.example.bitazzademo.ui.main.market.holder.MarketItemViewHolder
import com.example.bitazzademo.ui.main.market.holder.MarketListViewType

class MarketAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClicked: (productItem: MarketListViewType.Item) -> Unit = {}
    private var productList = mutableListOf<MarketListViewType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder = when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = ItemHeaderMarketBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MarketHeaderViewHolder(view)
            }

            else -> {
                val view = ItemMarketBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MarketItemViewHolder(view)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MarketItemViewHolder -> {
                holder.bind(productList[position])
            }

            is MarketHeaderViewHolder -> {
                holder.bind(productList[position])
            }
        }
    }

    fun updateItem(products: List<MarketListViewType>) = apply {
        productList = products.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_ITEM = 2
    }

}