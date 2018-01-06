package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviAction

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface RandomJokeAction : MviAction {
    @AutoValue
    abstract class LoadJokeAction : RandomJokeAction {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadJokeAction {
                return AutoValue_RandomJokeAction_LoadJokeAction(category)
            }
        }
    }
}