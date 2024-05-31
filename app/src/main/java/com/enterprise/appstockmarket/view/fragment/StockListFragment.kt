package com.enterprise.appstockmarket.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.TimeUtil
import com.enterprise.appstockmarket.databinding.FragmentStockListBinding
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import com.enterprise.appstockmarket.state.UiState
import com.enterprise.appstockmarket.view.adapter.StockMarketAdapter
import com.enterprise.appstockmarket.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StockListFragment : Fragment() {

    private var TAG = "StockListFragment"
    private lateinit var binding: FragmentStockListBinding
    private lateinit var mainViewModel : MainViewModel

    private lateinit var stockMarketAdapter: StockMarketAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStockListBinding.inflate(inflater, container, false)
        val view = binding.root

        stockMarketAdapter = StockMarketAdapter(activity as Context)
        binding.recyclerViewStockMarket.adapter = stockMarketAdapter

        mainViewModel.viewModelScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.currentStockPriceListFlow.collect { UiState ->

                    updateUI(UiState)

                }
            }
        }

        return view

    }

    private fun updateUI(uiState: UiState) {
        mainViewModel.viewModelScope.launch(Dispatchers.Main) {
            when (uiState) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.lastUpdateTime.visibility = View.GONE
                    binding.recyclerViewStockMarket.visibility = View.GONE
                }
                is UiState.Success -> {
                    binding.recyclerViewStockMarket.visibility = View.VISIBLE
                    stockMarketAdapter.stockList = uiState.stockList as ArrayList<Stock>
                    binding.lastUpdateTime.text = "${getString(R.string.main_fragment_last_update_time)}${TimeUtil.getCurrentFormattedTime()}"
                    binding.progressBar.visibility = View.GONE
                    binding.lastUpdateTime.visibility = View.VISIBLE
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}