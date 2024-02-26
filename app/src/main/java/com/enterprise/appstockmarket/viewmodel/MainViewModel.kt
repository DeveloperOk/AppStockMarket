package com.enterprise.appstockmarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import com.enterprise.appstockmarket.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel(){


    val currentStockPriceListFlow: StateFlow<List<Stock>?> = appRepository
        .stockList
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
        )


}

