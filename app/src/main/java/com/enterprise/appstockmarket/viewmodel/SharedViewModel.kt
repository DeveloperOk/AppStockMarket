package com.enterprise.appstockmarket.viewmodel


import androidx.lifecycle.ViewModel
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel(){

    private val TAG = "SharedViewModel"

    var selectedStock: Stock? = null

}
