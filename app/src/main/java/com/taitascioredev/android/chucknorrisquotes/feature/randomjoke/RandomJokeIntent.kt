package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviIntent

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface RandomJokeIntent : MviIntent {

    @AutoValue
    abstract class LoadIntent : RandomJokeIntent {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadIntent {
                return AutoValue_RandomJokeIntent_LoadIntent(category)
            }
        }
    }

    @AutoValue
    abstract class LoadNextIntent : RandomJokeIntent {
        abstract fun category(): String?
        companion object {
            fun create(category: String?): LoadNextIntent {
                return AutoValue_RandomJokeIntent_LoadNextIntent(category)
            }
        }
    }
}