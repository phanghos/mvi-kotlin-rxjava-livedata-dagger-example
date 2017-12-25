package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviIntent

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface JokeIntent : MviIntent {

    @AutoValue
    abstract class LoadIntent : JokeIntent {
        companion object {
            fun create(): LoadIntent {
                return AutoValue_JokeIntent_LoadIntent()
            }
        }
    }

    @AutoValue
    abstract class LoadNextIntent : JokeIntent {
        companion object {
            fun create(): LoadNextIntent {
                return AutoValue_JokeIntent_LoadNextIntent()
            }
        }
    }
}