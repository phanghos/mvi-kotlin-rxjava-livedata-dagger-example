package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.LceStatus
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviResult

/**
 * Created by rrtatasciore on 27/12/17.
 */
@AutoValue
abstract class JokesResult : MviResult {
    abstract fun status(): LceStatus
    abstract fun jokes(): List<Joke>?
    abstract fun error(): Throwable?
    companion object {
        fun create(status: LceStatus, jokes: List<Joke>?, error: Throwable?): JokesResult {
            return AutoValue_JokesResult(status, jokes, error)
        }
        fun success(jokes: List<Joke>) = create(LceStatus.SUCCESS, jokes, null)
        fun error(error: Throwable) = create(LceStatus.ERROR, null, error)
        fun inFlight() = create(LceStatus.IN_FLIGHT, null, null)
    }
}