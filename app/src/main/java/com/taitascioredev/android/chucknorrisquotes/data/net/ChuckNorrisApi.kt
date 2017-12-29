package com.taitascioredev.android.chucknorrisquotes.data.net

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeEntity
import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeQueryEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface ChuckNorrisApi {

    companion object {
        val BASE_URL = "https://api.chucknorris.io/"
    }

    @GET("jokes/random")
    fun getRandomJoke(@Query("query") category: String): Observable<JokeEntity>

    @GET("jokes/categories")
    fun getCategories(): Observable<List<String>>

    @GET("jokes/search")
    fun queryJokes(@Query("query") query: String): Observable<JokeQueryEntity>
}