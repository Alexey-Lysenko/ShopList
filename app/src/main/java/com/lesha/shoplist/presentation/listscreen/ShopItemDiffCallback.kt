package com.lesha.shoplist.presentation.listscreen

import androidx.recyclerview.widget.DiffUtil
import com.lesha.shoplist.domain.model.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return (oldItem == newItem)
    }

}