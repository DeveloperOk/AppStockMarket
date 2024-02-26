package com.enterprise.appstockmarket.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import com.enterprise.appstockmarket.repository.AppRepository
import com.enterprise.appstockmarket.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository, @ApplicationContext context: Context): ViewModel(){

    private val TAG = "MainViewModel"

    val currentStockPriceListFlow: StateFlow<UiState> = appRepository
        .stockList
        .map{ stockPriceList ->
            UiState.Success(stockPriceList) as UiState
        }
        .retry { throwable ->
            Log.d(TAG, "Flow retry: ${throwable.message}")
            delay(3000)
            true
        }
        .onCompletion {
            Log.d(TAG, "Flow has completed.")
        }.catch{throwable ->
            Log.d(TAG, "Flow catch: ${throwable.message}")
            emit(UiState.Error(context.getString(R.string.main_view_model_error_message)))
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = UiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
        )


}

