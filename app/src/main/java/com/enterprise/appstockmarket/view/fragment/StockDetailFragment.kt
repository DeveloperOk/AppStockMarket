package com.enterprise.appstockmarket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.databinding.FragmentStockDetailBinding
import com.enterprise.appstockmarket.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockDetailFragment : Fragment() {

    private var TAG = "StockDetailFragment"

    private lateinit var binding: FragmentStockDetailBinding

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[SharedViewModel::class.java]

        navController = findNavController()

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                navController.popBackStack()

            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStockDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.stockNameValue.text = sharedViewModel.selectedStock?.name?.trim()
        binding.stockPriceValue.text = "${getString(R.string.stock_detail_fragment_fragment_usd_sign)}${sharedViewModel.selectedStock?.currentPrice}".trim()

        return view
    }



}