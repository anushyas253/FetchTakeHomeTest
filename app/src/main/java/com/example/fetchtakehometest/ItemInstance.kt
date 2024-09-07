package com.example.fetchtakehometest

import com.example.fetchtakehometest.model.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ItemInstance {

    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}