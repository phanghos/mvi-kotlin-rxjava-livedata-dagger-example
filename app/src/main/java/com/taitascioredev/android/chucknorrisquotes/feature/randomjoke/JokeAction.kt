package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviAction

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeAction : MviAction {

    @AutoValue
    abstract class LoadJokeAction : JokeAction {
        companion object {
            fun create(): LoadJokeAction {
                return AutoValue_JokeAction_LoadJokeAction()
            }
        }
    }

    @AutoValue
    abstract class LoadNextJokeAction : JokeAction {
        companion object {
            fun create(): LoadNextJokeAction {
                return AutoValue_JokeAction_LoadNextJokeAction()
            }
        }
    }
}