package com.lesha.shoplist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lesha.shoplist.R
import com.lesha.shoplist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem,ShopListAdapter.ShopIemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener:((ShopItem)-> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    class ShopIemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopItemName: TextView = view.findViewById(R.id.shop_item_name)
        val shopItemCount: TextView = view.findViewById(R.id.shop_item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopIemViewHolder {
        val layout = when (viewType) {
            ENABLED_ITEM_TYPE -> R.layout.item_shop_enabled
            DISABLED_ITEM_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopIemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopIemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.shopItemName.text = shopItem.name
        holder.shopItemCount.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            ENABLED_ITEM_TYPE
        } else {
            DISABLED_ITEM_TYPE
        }
    }

    override fun onViewRecycled(holder: ShopIemViewHolder) {
        super.onViewRecycled(holder)
        holder.shopItemName.text = ""
        holder.shopItemCount.text =""
        holder.shopItemName.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                android.R.color.white
            )
        )
    }

    companion object {
        const val ENABLED_ITEM_TYPE = 112
        const val DISABLED_ITEM_TYPE = 113
        const val POOL_OF_VIEW_HOLDERS = 11
    }
}