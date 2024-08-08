package com.example.myapplication.ApiServices

import com.example.myapplication.model.Coin
import retrofit2.http.GET

interface CoinPaprikaApiService {
    @GET("/v1/tickers")
    suspend fun getCoins(): List<Coin>
}