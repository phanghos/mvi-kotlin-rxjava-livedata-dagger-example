package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviIntent

/**
 * Created by rrtatasciore on 25/12/17.
 */
interface CategoryIntent : MviIntent {

    @AutoValue
    abstract class InitialIntent : CategoryIntent {
        companion object {
            fun create(): InitialIntent {
                return AutoValue_CategoryIntent_InitialIntent()
            }
        }
    }

    @AutoValue
    abstract class LoadIntent : CategoryIntent {
        companion object {
            fun create(): LoadIntent {
                return AutoValue_CategoryIntent_LoadIntent()
            }
        }
    }
}