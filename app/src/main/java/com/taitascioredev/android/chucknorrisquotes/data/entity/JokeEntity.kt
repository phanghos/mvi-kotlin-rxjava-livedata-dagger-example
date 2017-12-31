package com.taitascioredev.android.chucknorrisquotes.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rrtatasciore on 31/12/17.
 */
data class JokeEntity(
        @SerializedName("categories") val category: List<String>?,
        @SerializedName("icon_url") val iconUrl: String,
        @SerializedName("id") val id: String,
        @SerializedName("url") val url: String,
        @SerializedName("value") val value: String
)