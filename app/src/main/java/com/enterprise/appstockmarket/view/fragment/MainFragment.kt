package com.enterprise.appstockmarket.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.enterprise.appstockmarket.databinding.FragmentMainBinding
import com.enterprise.appstockmarket.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var TAG = "MainFragment"
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel :MainViewModel

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

        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        mainViewModel.viewModelScope.launch(Dispatchers.IO) {
            mainViewModel.currentStockPriceListFlow.collect { stockList ->

                Log.d(TAG, "Stock name: ${stockList?.first()?.name} and Stock price: ${stockList?.first()?.currentPrice}")

            }
        }

        return view

    }


}