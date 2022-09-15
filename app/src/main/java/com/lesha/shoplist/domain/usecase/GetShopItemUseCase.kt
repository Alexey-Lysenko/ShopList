package com.lesha.shoplist.domain.usecase

import com.lesha.shoplist.domain.model.ShopItem
import com.lesha.shoplist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}