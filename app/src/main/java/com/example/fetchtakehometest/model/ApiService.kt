package com.example.fetchtakehometest.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json") // Endpoint URL for fetching items
    fun getItems(): Call<List<ItemData>>
}