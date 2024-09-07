package com.example.fetchtakehometest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fetchtakehometest.model.ApiService
import com.example.fetchtakehometest.model.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ItemRepository {
    fun fetchItems(): MutableLiveData<List<ItemData>?>
}