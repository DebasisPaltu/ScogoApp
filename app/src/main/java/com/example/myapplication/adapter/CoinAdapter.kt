package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Coin


class CoinAdapter(
    private val coins: List<Coin>,
    private val onCoinClicked: (Coin) -> Unit
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position], onCoinClicked)
    }

    override fun getItemCount(): Int = coins.size

    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coinName = itemView.findViewById<TextView>(R.id.coinName)
        private val coinSymbol = itemView.findViewById<TextView>(R.id.coinSymbol)
        private val coinPrice = itemView.findViewById<TextView>(R.id.coinPrice)

        fun bind(coin: Coin, onCoinClicked: (Coin) -> Unit) {
            coinName.text = coin.name
            coinSymbol.text = coin.symbol
            coinPrice.text = coin.quotes.usd.price?.toString() ?: "Price not available"
            itemView.setOnClickListener { onCoinClicked(coin) }
        }
    }
}

