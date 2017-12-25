package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviAction

/**
 * Created by rrtatasciore on 25/12/17.
 */
interface CategoryAction : MviAction {
    @AutoValue
    abstract class LoadCategoriesAction : CategoryAction {
        companion object {
            fun create(): LoadCategoriesAction {
                return AutoValue_CategoryAction_LoadCategoriesAction()
            }
        }
    }
}