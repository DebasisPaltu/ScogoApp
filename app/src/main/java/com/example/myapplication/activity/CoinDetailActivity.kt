package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CoinDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val coinName = intent.getStringExtra("coin_name")
        val coinSymbol = intent.getStringExtra("coin_symbol")
        val coinPrice = intent.getStringExtra("coin_price")

        val nameTextView = findViewById<TextView>(R.id.coinNameDetail)
        val symbolTextView = findViewById<TextView>(R.id.coinSymbolDetail)
        val priceTextView = findViewById<TextView>(R.id.coinPriceDetail)

        nameTextView.text = coinName
        symbolTextView.text = coinSymbol
        priceTextView.text = coinPrice

    }
}