package com.enterprise.appstockmarket.repository

import android.util.Log
import com.enterprise.appstockmarket.remotedatasource.mock.FlowMockApi
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepository @Inject constructor(private val flowMockApi: FlowMockApi){

    private val TAG = "AppRepository"

    val stockList: Flow<List<Stock>> = flow {

        while (true) {

            Log.d(TAG,"Retrieving current stock prices")

            val currentStockList = flowMockApi.getCurrentStockPrices()
            emit(currentStockList)

            delay(3000)

        }

    }

}