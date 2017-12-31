package com.taitascioredev.android.chucknorrisquotes.model

/**
 * Created by rrtatasciore on 31/12/17.
 */
data class Joke(
        val category: List<String>?,
        val iconUrl: String,
        val id: String,
        val url: String,
        val value: String
)