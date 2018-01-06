package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.Joke

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokeQuery(val total: Int, val result: List<Joke>?)