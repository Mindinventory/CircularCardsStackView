package com.mindinventory.circularcardsstackview.data


import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("primaryTitle")
    val primaryTitle: String = "",
    @SerializedName("imageUrl")
    val image: Any? = null,
    @SerializedName("secondaryTitle")
    val secondaryTitle: String = ""
)