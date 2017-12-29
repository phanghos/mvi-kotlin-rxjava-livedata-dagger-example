package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviIntent

/**
 * Created by rrtatasciore on 27/12/17.
 */
interface JokesIntent : MviIntent {

    @AutoValue
    abstract class InitialIntent : JokesIntent {
        abstract fun category(): String
        companion object {
            fun create(category: String): InitialIntent {
                return AutoValue_JokesIntent_InitialIntent(category)
            }
        }
    }

    @AutoValue
    abstract class LoadJokesIntent : JokesIntent {
        abstract fun category(): String
        companion object {
            fun create(category: String): LoadJokesIntent {
                return AutoValue_JokesIntent_LoadJokesIntent(category)
            }
        }
    }
}