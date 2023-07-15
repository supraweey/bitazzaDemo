package com.example.bitazzademo.ui.main.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitazzademo.databinding.ItemHeaderMarketBinding
import com.example.bitazzademo.databinding.ItemMarketBinding
import com.example.bitazzademo.ui.main.market.holder.ProductHeaderViewHolder
import com.example.bitazzademo.ui.main.market.holder.ProductItemViewHolder
import com.example.bitazzademo.ui.main.market.holder.ProductListViewType

class ProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var productList = mutableListOf<ProductListViewType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder = when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view = ItemMarketBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ProductItemViewHolder(view)
            }

            else -> {
                val view = ItemHeaderMarketBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ProductHeaderViewHolder(view)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductItemViewHolder -> {
                holder.bind(productList[position], position)
            }

            is ProductHeaderViewHolder -> {
                holder.bind(productList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (productList[position]) {
        is ProductListViewType.Item -> VIEW_TYPE_ITEM
        is ProductListViewType.Header -> VIEW_TYPE_HEADER
    }

    fun updateItem(products: List<ProductListViewType>) = apply {
        productList = products.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_HEADER = 2
    }

}