package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviIntent

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeIntent : MviIntent {

    @AutoValue
    abstract class LoadIntent : JokeIntent {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadIntent {
                return AutoValue_JokeIntent_LoadIntent(category)
            }
        }
    }

    @AutoValue
    abstract class LoadNextIntent : JokeIntent {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadNextIntent {
                return AutoValue_JokeIntent_LoadNextIntent(category)
            }
        }
    }
}