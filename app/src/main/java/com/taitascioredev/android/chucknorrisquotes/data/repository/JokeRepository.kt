package com.taitascioredev.android.chucknorrisquotes.data.repository

import com.taitascioredev.android.chucknorrisquotes.model.Joke
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeRepository {
    fun getRandomJoke(): Observable<Joke>
}