package com.enterprise.appstockmarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enterprise.appstockmarket.R
import com.enterprise.appstockmarket.remotedatasource.mock.Stock

class StockMarketAdapter(val context: Context) :
    RecyclerView.Adapter<StockMarketAdapter.StockMarketViewHolder>() {

    var stockList: ArrayList<Stock> = ArrayList<Stock>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class StockMarketViewHolder(itemView: View, stockList: ArrayList<Stock>?, context: Context) : RecyclerView.ViewHolder(itemView) {

        private val textViewRank: TextView = itemView.findViewById(R.id.textViewRank)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)

        fun bind(stock: Stock, context: Context) {

            textViewRank.text = stock.rank.toString()
            textViewName.text = stock.name
            textViewPrice.text = "${context.getString(R.string.main_fragment_usd_sign)}${stock.currentPrice}"

        }

        init{

            itemView.setOnClickListener{

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockMarketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_row, parent, false)

        return StockMarketViewHolder(view, stockList, context)
    }

    override fun getItemCount(): Int {

        return stockList.size

    }

    override fun onBindViewHolder(holder: StockMarketViewHolder, position: Int) {

        stockList.get(position)?.let { holder.bind(it, context) }

    }
}