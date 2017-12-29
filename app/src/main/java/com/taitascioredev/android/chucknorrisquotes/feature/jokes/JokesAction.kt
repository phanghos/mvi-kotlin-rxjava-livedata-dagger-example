package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviAction

/**
 * Created by rrtatasciore on 27/12/17.
 */
@AutoValue
abstract class JokesAction : MviAction {
    abstract fun category(): String
    companion object {
        fun create(category: String): JokesAction {
            return AutoValue_JokesAction(category)
        }
    }
}