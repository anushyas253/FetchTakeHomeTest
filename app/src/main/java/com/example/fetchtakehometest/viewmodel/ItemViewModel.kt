package com.example.fetchtakehometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchtakehometest.ItemInstance
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.repository.ItemRepository
import com.example.fetchtakehometest.repository.ItemRepositoryImpl
import kotlinx.coroutines.launch


class ItemViewModel : ViewModel() {

    private val repository = ItemRepositoryImpl(ItemInstance.apiService)
    val items: MutableLiveData<List<ItemData>?> = repository.fetchItems()

     fun fetchItems() {
        repository.fetchItems()
    }
}