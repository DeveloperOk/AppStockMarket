package com.enterprise.appstockmarket.state

import com.enterprise.appstockmarket.remotedatasource.mock.Stock

sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}