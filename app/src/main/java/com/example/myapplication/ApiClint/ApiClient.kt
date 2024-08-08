package com.example.myapplication.ApiClint

import com.example.myapplication.ApiServices.CoinPaprikaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.coinpaprika.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: CoinPaprikaApiService = retrofit.create(CoinPaprikaApiService::class.java)
}