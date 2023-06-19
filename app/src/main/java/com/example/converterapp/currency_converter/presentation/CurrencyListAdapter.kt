package com.example.converterapp.currency_converter.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.converterapp.R
import com.example.converterapp.currency_converter.domain.CurrencyRates


class CurrencyListAdapter (private val list: ArrayList<CurrencyRates>):  BaseAdapter() {

     class CountryViewHolder(view: View?){

        var label: TextView?  = null
        init {
            if(view != null)
            label = view.findViewById(R.id.text1) as TextView?
        }


    }
    override fun getCount(): Int =  list.size

    override fun getItem(p0: Int): Any  = list[p0]

    override fun getItemId(p0: Int): Long  = p0.toLong();

    override fun hasStableIds(): Boolean = true

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View  = initView(p0, p1, p2);


    override fun getItemViewType(p0: Int): Int  = 1

    override fun getViewTypeCount(): Int  =1


    override fun getDropDownView(p0: Int, p1: View?, p2: ViewGroup?): View  =
        initView(p0, p1, p2)

    private fun initView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val vh: CountryViewHolder
        if (p1 == null) {
            val inflater = LayoutInflater.from(p2?.context)
            view = inflater.inflate(R.layout.layout_item_spinner, p2, false)
            vh = CountryViewHolder(view)
            view?.tag = vh
        } else {
            view = p1
            vh = view.tag as CountryViewHolder
        }
        vh.label?.text = list.get(p0).country
        return view
    }
}