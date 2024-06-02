package com.enterprise.appstockmarket.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.databinding.StockRowBinding
import com.enterprise.appstockmarket.remotedatasource.mock.PriceTrend
import com.enterprise.appstockmarket.remotedatasource.mock.Stock
import com.enterprise.appstockmarket.viewmodel.SharedViewModel

class StockMarketAdapter(val context: Context, val navController: NavController, val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<StockMarketAdapter.StockMarketViewHolder>() {

    var stockList: ArrayList<Stock> = ArrayList<Stock>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class StockMarketViewHolder(private val binding : StockRowBinding, itemView: View, stockList: ArrayList<Stock>?,
                                context: Context, private val navController: NavController,
        private val sharedViewModel: SharedViewModel) : RecyclerView.ViewHolder(itemView) {

        init{

            binding.constraintLayoutStockRow.setOnClickListener{

                sharedViewModel.selectedStock = stockList?.get(adapterPosition)
                navController.navigate(R.id.action_stockListFragment_to_stockDetailFragment)

            }

        }
        
        fun bind(stock: Stock, context: Context) {

            binding.textViewRank.text = stock.rank.toString()
            binding.textViewName.text = stock.name
            binding.textViewPrice.text = "${context.getString(R.string.main_fragment_usd_sign)}${stock.currentPrice}"

            setBackgroundDrawableOfStockRow(stock, context)
            setImageViewPriceTrendArrow(stock)

        }

        private fun setImageViewPriceTrendArrow(stock: Stock) {

            var backgroundDrawableID = R.drawable.arrow_neutral_no_image
            when (stock.priceTrend) {
                PriceTrend.UP -> {
                    backgroundDrawableID = R.drawable.arrow_upward
                }

                PriceTrend.DOWN -> {
                    backgroundDrawableID = R.drawable.arrow_downward
                }

                PriceTrend.NEUTRAL -> {
                    backgroundDrawableID = R.drawable.arrow_neutral_no_image
                }

                PriceTrend.UNKNOWN -> {
                    backgroundDrawableID = R.drawable.arrow_neutral_no_image
                }
                else -> {
                    backgroundDrawableID = R.drawable.arrow_neutral_no_image
                }
            }

            binding.imageViewPriceTrendArrow.setImageResource(backgroundDrawableID)

        }

        private fun setBackgroundDrawableOfStockRow(
            stock: Stock,
            context: Context
        ) {
            var backgroundDrawable: Drawable? = null
            when (stock.priceTrend) {
                PriceTrend.UP -> {
                    backgroundDrawable =
                        ContextCompat.getDrawable(context, R.drawable.stock_row_up_background)
                }

                PriceTrend.DOWN -> {
                    backgroundDrawable =
                        ContextCompat.getDrawable(context, R.drawable.stock_row_down_background)
                }

                PriceTrend.NEUTRAL -> {
                    backgroundDrawable =
                        ContextCompat.getDrawable(context, R.drawable.stock_row_neutral_background)
                }

                PriceTrend.UNKNOWN -> {
                    backgroundDrawable =
                        ContextCompat.getDrawable(context, R.drawable.stock_row_neutral_background)
                }

                else -> {
                    backgroundDrawable =
                        ContextCompat.getDrawable(context, R.drawable.stock_row_neutral_background)
                }
            }
            binding.constraintLayoutStockRow.setBackground(backgroundDrawable);
            binding.constraintLayoutStockRow.setPadding(10,10,10,10)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockMarketViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StockRowBinding.inflate(layoutInflater, parent, false)
        val itemView = binding.root

        return StockMarketViewHolder(binding, itemView, stockList, context, navController, sharedViewModel)

    }

    override fun getItemCount(): Int {

        return stockList.size

    }

    override fun onBindViewHolder(holder: StockMarketViewHolder, position: Int) {

        stockList.get(position)?.let { holder.bind(it, context) }

    }
}