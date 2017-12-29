package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.model.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewState

/**
 * Created by rrtatasciore on 27/12/17.
 */
@AutoValue
abstract class JokesViewState : MviViewState {
    abstract fun loading(): Boolean
    abstract fun jokes(): List<Joke>?
    abstract fun error(): Throwable?
    companion object {
        fun create(loading: Boolean, jokes: List<Joke>?, error: Throwable?): JokesViewState {
            return AutoValue_JokesViewState(loading, jokes, error)
        }
        fun idle() = create(false, null, null)
    }
}