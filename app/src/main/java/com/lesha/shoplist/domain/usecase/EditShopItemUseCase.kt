package com.lesha.shoplist.domain.usecase

import com.lesha.shoplist.domain.model.ShopItem
import com.lesha.shoplist.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}