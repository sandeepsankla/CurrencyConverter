package com.example.converterapp.currency_converter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.R
import com.example.converterapp.currency_converter.domain.CurrencyRates
import com.example.converterapp.databinding.ItemConverterLayoutBinding
import com.example.converterapp.databinding.ItemConverterLayoutBindingImpl
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CurrencyAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: ArrayList<CurrencyRates> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemConverterLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_converter_layout,
            parent,
            false
        )
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CurrencyViewHolder)
        viewHolder.bind(list[position])
    }

    override fun getItemCount(): Int  = list.size

    fun updateList(data: ArrayList<CurrencyRates>) {
        list.clear()
        list.addAll(data)
    }

    inner class CurrencyViewHolder(val binding: ItemConverterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyRates: CurrencyRates) {
            binding.tvItem.text = currencyRates.getDisplayText()
        }

    }
}