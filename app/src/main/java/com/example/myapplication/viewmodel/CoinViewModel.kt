package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ApiClint.ApiClient
import com.example.myapplication.model.Coin
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {
    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCoins() {
        viewModelScope.launch {
            try {
                val coinList = ApiClient.apiService.getCoins()
                _coins.postValue(coinList)
            } catch (e: Exception) {
                _error.postValue("Failed to load data")
            }
        }
    }

    fun searchCoins(query: String) {
        _coins.value?.let {
            _coins.value = it.filter { coin ->
                coin.name.contains(query, ignoreCase = true)
            }
        }
    }
}