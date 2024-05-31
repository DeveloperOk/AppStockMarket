package com.enterprise.appstockmarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.databinding.StockRowBinding
import com.enterprise.appstockmarket.remotedatasource.mock.Stock

class StockMarketAdapter(val context: Context) :
    RecyclerView.Adapter<StockMarketAdapter.StockMarketViewHolder>() {

    var stockList: ArrayList<Stock> = ArrayList<Stock>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class StockMarketViewHolder(private val binding : StockRowBinding, itemView: View, stockList: ArrayList<Stock>?, context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bind(stock: Stock, context: Context) {

            binding.textViewRank.text = stock.rank.toString()
            binding.textViewName.text = stock.name
            binding.textViewPrice.text = "${context.getString(R.string.main_fragment_usd_sign)}${stock.currentPrice}"

        }

        init{

            binding.constraintLayoutStockRow.setOnClickListener{

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockMarketViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StockRowBinding.inflate(layoutInflater, parent, false)
        val itemView = binding.root

        return StockMarketViewHolder(binding, itemView, stockList, context)

    }

    override fun getItemCount(): Int {

        return stockList.size

    }

    override fun onBindViewHolder(holder: StockMarketViewHolder, position: Int) {

        stockList.get(position)?.let { holder.bind(it, context) }

    }
}