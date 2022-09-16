package com.lesha.shoplist.domain.usecase

import com.lesha.shoplist.domain.model.ShopItem
import com.lesha.shoplist.domain.repository.ShopListRepository

class AddShoItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}