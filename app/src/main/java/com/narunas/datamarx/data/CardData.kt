package com.narunas.datamarx.data

import com.google.gson.annotations.SerializedName

data class CardData(var timestamp: Long) {

    @SerializedName("name")
    var title: String = ""

    @SerializedName("thumbnailUrl")
    val thumbUrl = ""

    @SerializedName("contentUrl")
    var mainUrl: String = ""




}