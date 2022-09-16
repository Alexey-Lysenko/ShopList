package com.lesha.shoplist.presentation.listscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lesha.shoplist.R
import com.lesha.shoplist.domain.model.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopIemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener:((ShopItem)-> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

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

    companion object {
        const val ENABLED_ITEM_TYPE = 112
        const val DISABLED_ITEM_TYPE = 113
        const val POOL_OF_VIEW_HOLDERS = 11
    }
}