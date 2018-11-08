package com.narunas.datamarx.data

import com.google.gson.annotations.SerializedName

data class SectionData(var title: String?) {

    @SerializedName("value")
    var cards = arrayListOf(CardData(System.currentTimeMillis()))


}