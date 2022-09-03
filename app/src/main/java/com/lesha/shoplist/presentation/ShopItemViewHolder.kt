package com.lesha.shoplist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lesha.shoplist.R

class ShopIemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val shopItemName: TextView = view.findViewById(R.id.shop_item_name)
    val shopItemCount: TextView = view.findViewById(R.id.shop_item_count)
}