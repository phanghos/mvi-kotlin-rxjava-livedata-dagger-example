package com.taitascioredev.android.chucknorrisquotes.data.net

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface ChuckNorrisApi {

    companion object {
        val BASE_URL = "https://api.chucknorris.io/"
    }

    @GET("jokes/random")
    fun getRandomJoke(): Observable<JokeEntity>

    @GET("jokes/categories")
    fun getCategories(): Observable<List<String>>
}