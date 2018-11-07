package com.narunas.datamarx.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.narunas.datamarx.R
import com.narunas.datamarx.data.CardData
import com.narunas.datamarx.ui.BaseImageView

class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet =  ArrayList<CardData>()


    fun setData(data: ArrayList<CardData>) {
        dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card, viewGroup, false)
        return DatamarxViewholder(v)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is DatamarxViewholder) {

            val card = dataSet[position]
            holder.subTite.text = card.sub_title
            holder.title.text = card.title
            holder.image.imageSource(card.url)

        }

    }

    inner class DatamarxViewholder(v: View) : RecyclerView.ViewHolder(v) {
        var image: BaseImageView
        var title: TextView
        var subTite: TextView

        init {
            image = v.findViewById(R.id.card_image)
            title = v.findViewById(R.id.card_title)
            subTite = v.findViewById(R.id.card_subtitle)
        }

    }
}