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

    // Initialize RepositoryImpl directly with ApiService
    private val repository = ItemRepositoryImpl(ItemInstance.apiService)

    // LiveData exposed to the View
    val items: MutableLiveData<List<ItemData>?> = repository.fetchItems()

    // Function to fetch items (could add more logic here if needed)
    fun fetchItems() {
        repository.fetchItems()
    }
}