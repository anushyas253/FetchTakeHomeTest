package com.example.fetchtakehometest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fetchtakehometest.model.ApiService
import com.example.fetchtakehometest.model.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepositoryImpl(private val apiService: ApiService) : ItemRepository {

    private val itemsLiveData = MutableLiveData<List<ItemData>?>()

    override fun fetchItems(): MutableLiveData<List<ItemData>?> {
        apiService.getItems().enqueue(object : Callback<List<ItemData>> {
            override fun onResponse(
                call: Call<List<ItemData>>,
                response: Response<List<ItemData>>
            ) {
                if (response.isSuccessful) {
                    val sortedItems = response.body()
                    itemsLiveData.value = sortedItems
                }
            }

            override fun onFailure(call: Call<List<ItemData>>, t: Throwable) {
                itemsLiveData.value = emptyList()
            }
        })

        return itemsLiveData
    }
}