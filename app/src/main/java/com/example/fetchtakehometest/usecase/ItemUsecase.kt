package com.example.fetchtakehometest.usecase

import androidx.lifecycle.MutableLiveData
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.repository.ItemRepository

interface ItemUsecase {

    val itemRepository: ItemRepository
    fun execute(): MutableLiveData<List<ItemData>?>

}