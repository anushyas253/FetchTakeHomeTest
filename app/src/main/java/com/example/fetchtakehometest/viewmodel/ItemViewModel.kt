package com.example.fetchtakehometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.usecase.ItemUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemUseCase: ItemUsecase
) : ViewModel() {

    val items: LiveData<List<ItemData>?> = itemUseCase.execute()

    fun fetchItems() {
        itemUseCase.execute()
    }
}