package com.lesha.shoplist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lesha.shoplist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerViewShopList = findViewById<RecyclerView>(R.id.recycler_view_shop_list)
        recyclerViewShopList.also {
            adapter = ShopListAdapter()
            it.adapter = adapter
            it.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLED_ITEM_TYPE,
                ShopListAdapter.POOL_OF_VIEW_HOLDERS
            )
            it.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLED_ITEM_TYPE,
                ShopListAdapter.POOL_OF_VIEW_HOLDERS
            )

        }
    }

}