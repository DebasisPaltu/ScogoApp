package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import com.example.myapplication.adapter.CoinAdapter
import com.example.myapplication.viewmodel.CoinViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.coins.observe(this) { coins ->
            adapter = CoinAdapter(coins) { coin ->
                val intent = Intent(this, CoinDetailActivity::class.java).apply {
                    putExtra("coin_name", coin.name)
                    Log.e("tdhd",coin.name.toString())
                    putExtra("coin_symbol", coin.symbol)
                    putExtra("coin_price", coin.quotes.usd.price?.toString() ?: "Price not available")
                }
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.fetchCoins()

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCoins()
            swipeRefreshLayout.isRefreshing = false
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchCoins(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchCoins(it) }
                return true
            }
        })
    }
}