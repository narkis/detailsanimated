package com.narunas.datamarx.ui.adapters

import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair as UtilPair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.narunas.datamarx.DetailsActivity
import com.narunas.datamarx.MainActivity
import com.narunas.datamarx.R
import com.narunas.datamarx.data.CardData
import com.narunas.datamarx.data.DetailsModel.Companion.CardInDetail
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
            holder.title.text = card.title
            holder.image.imageSource(card.thumbUrl, true)

            holder.itemView.setOnClickListener {
                it?.let {
                    CardInDetail.postValue(card)
                    val intent = Intent(holder.itemView.context, DetailsActivity::class.java)

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        holder.itemView.context as MainActivity,
                        UtilPair<View, String>(holder.image,
                            DetailsActivity.MAIN_IMAGE_TRANSITION),
                        UtilPair<View, String>(holder.title,
                            DetailsActivity.MAIN_TITLE_TRANSITION)
                    )

                    ActivityCompat.startActivity(holder.itemView.context, intent, options.toBundle())

                }
            }
        }

    }

    inner class DatamarxViewholder(v: View) : RecyclerView.ViewHolder(v) {
        var image: BaseImageView
        var title: TextView

        init {
            image = v.findViewById(R.id.card_image)
            title = v.findViewById(R.id.card_title)
        }

    }
}