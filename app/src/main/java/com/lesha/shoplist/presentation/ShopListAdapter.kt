package com.lesha.shoplist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lesha.shoplist.R
import com.lesha.shoplist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.shopIemViewHolder>() {

    var shopList = listOf<ShopItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class shopIemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopItemName = view.findViewById<TextView>(R.id.shop_item_name)
        val shopItemCount = view.findViewById<TextView>(R.id.shop_item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopIemViewHolder {
        val layout = when (viewType) {
            ENABLED_ITEM_TYPE -> R.layout.item_shop_enabled
            DISABLED_ITEM_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return shopIemViewHolder(view)
    }

    override fun onBindViewHolder(holder: shopIemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.shopItemName.text = shopItem.name
        holder.shopItemCount.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) {
            ENABLED_ITEM_TYPE
        } else {
            DISABLED_ITEM_TYPE
        }
    }

    override fun onViewRecycled(holder: shopIemViewHolder) {
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

    override fun getItemCount(): Int {
        return shopList.size
    }

    companion object {
        const val ENABLED_ITEM_TYPE = 112
        const val DISABLED_ITEM_TYPE = 113
        const val POOL_OF_VIEW_HOLDERS = 11
    }
}