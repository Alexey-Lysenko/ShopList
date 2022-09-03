package com.lesha.shoplist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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
            adapter.submitList(it)
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
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(recyclerViewShopList)
    }

    private fun setupSwipeListener(recyclerViewShopList: RecyclerView?) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(item)
            }
        }
        ItemTouchHelper(callBack).attachToRecyclerView(recyclerViewShopList)
    }

    private fun setupClickListener() {
        adapter.onShopItemClickListener = {
            viewModel.editShopItem(it)
        }
    }

    private fun setupLongClickListener() {
        adapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

}