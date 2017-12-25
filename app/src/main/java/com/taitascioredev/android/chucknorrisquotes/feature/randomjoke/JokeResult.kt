package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.LceStatus
import com.taitascioredev.android.chucknorrisquotes.model.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviResult

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeResult : MviResult {

    @AutoValue
    abstract class LoadJokeResult : JokeResult {

        abstract fun status(): LceStatus

        abstract fun joke(): Joke?

        abstract fun error(): Throwable?

        companion object {
            fun success(joke: Joke): LoadJokeResult {
                return AutoValue_JokeResult_LoadJokeResult(LceStatus.SUCCESS, joke, null)
            }

            fun error(error: Throwable): LoadJokeResult {
                return AutoValue_JokeResult_LoadJokeResult(LceStatus.ERROR, null, error)
            }

            fun inFlight(): LoadJokeResult {
                return AutoValue_JokeResult_LoadJokeResult(LceStatus.IN_FLIGHT, null, null)
            }
        }
    }
}