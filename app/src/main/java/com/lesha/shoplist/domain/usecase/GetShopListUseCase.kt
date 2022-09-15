package com.lesha.shoplist.domain.usecase

import androidx.lifecycle.LiveData
import com.lesha.shoplist.domain.model.ShopItem
import com.lesha.shoplist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository){
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}