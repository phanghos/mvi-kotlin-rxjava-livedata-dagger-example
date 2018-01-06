package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewState

/**
 * Created by rrtatasciore on 24/12/17.
 */
@AutoValue
abstract class RandomJokeViewState : MviViewState {

    abstract fun loading(): Boolean

    abstract fun joke(): Joke?

    abstract fun error(): Throwable?

    companion object {
        fun create(loading: Boolean, joke: Joke?, error: Throwable?): RandomJokeViewState {
            return AutoValue_RandomJokeViewState(loading, joke, error)
        }

        fun idle(): RandomJokeViewState {
            return create(false, null, null)
        }
    }
}