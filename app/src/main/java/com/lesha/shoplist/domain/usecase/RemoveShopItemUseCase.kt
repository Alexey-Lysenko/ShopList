package com.lesha.shoplist.domain.usecase

import com.lesha.shoplist.domain.model.ShopItem
import com.lesha.shoplist.domain.repository.ShopListRepository

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun removeShopItem(shopItem: ShopItem){
        shopListRepository.removeShopItem(shopItem)
    }
}