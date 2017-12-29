package com.taitascioredev.android.chucknorrisquotes.data.net

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class ChuckNorrisService(private val api: ChuckNorrisApi) {
    fun getRandomJoke(category: String?) = api.getRandomJoke(category ?: "")
    fun getCategories() = api.getCategories()
    fun queryJokes(query: String) = api.queryJokes(query)
}