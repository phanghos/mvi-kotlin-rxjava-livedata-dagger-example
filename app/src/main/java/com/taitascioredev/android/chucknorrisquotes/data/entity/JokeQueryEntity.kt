package com.taitascioredev.android.chucknorrisquotes.data.entity

import com.google.gson.annotations.SerializedName
import com.taitascioredev.android.chucknorrisquotes.model.Joke

/**
 * Created by rrtatasciore on 31/12/17.
 */
data class JokeQueryEntity(
        @SerializedName("total")  val total: Int,
        @SerializedName("result") val result: List<Joke>
)