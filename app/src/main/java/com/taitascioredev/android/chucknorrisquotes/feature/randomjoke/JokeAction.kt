package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviAction

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeAction : MviAction {

    @AutoValue
    abstract class LoadJokeAction : JokeAction {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadJokeAction {
                return AutoValue_JokeAction_LoadJokeAction(category)
            }
        }
    }

    @AutoValue
    abstract class LoadNextJokeAction : JokeAction {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadNextJokeAction {
                return AutoValue_JokeAction_LoadNextJokeAction(category)
            }
        }
    }
}