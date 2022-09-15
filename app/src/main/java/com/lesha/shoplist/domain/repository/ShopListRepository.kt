package com.lesha.shoplist.domain.repository

import androidx.lifecycle.LiveData
import com.lesha.shoplist.domain.model.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}