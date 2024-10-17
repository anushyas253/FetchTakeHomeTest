package com.example.fetchtakehometest.usecase

import androidx.lifecycle.MutableLiveData
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemUseCaseImpl @Inject constructor(
    override val itemRepository: ItemRepository
): ItemUsecase{

    override fun execute(): MutableLiveData<List<ItemData>?> {
        return itemRepository.fetchItems()
    }

}