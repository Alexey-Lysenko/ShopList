package com.lesha.shoplist.presentation.listscreen

import androidx.lifecycle.ViewModel
import com.lesha.shoplist.data.ShopListRepositoryImpl
import com.lesha.shoplist.domain.usecase.EditShopItemUseCase
import com.lesha.shoplist.domain.usecase.GetShopListUseCase
import com.lesha.shoplist.domain.usecase.RemoveShopItemUseCase
import com.lesha.shoplist.domain.model.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem){
        removeShopItemUseCase.removeShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
    }
}