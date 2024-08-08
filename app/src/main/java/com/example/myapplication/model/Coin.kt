package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val total_supply: Double?,
    val max_supply: Double?,
    val beta_value: Double?,
    val first_data_at: String?,
    val last_updated: String?,
    val quotes: Quotes
)

data class Quotes(
    @SerializedName("USD") val usd: Quote
)

data class Quote(
    val price: Double?,
    val volume_24h: Double?,
    val market_cap: Double?
)
