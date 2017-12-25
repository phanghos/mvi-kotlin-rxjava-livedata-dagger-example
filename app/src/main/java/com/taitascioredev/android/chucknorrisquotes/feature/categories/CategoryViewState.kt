package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewState

/**
 * Created by rrtatasciore on 25/12/17.
 */
@AutoValue
abstract class CategoryViewState : MviViewState {

    abstract fun loading(): Boolean

    abstract fun categories(): List<String>?

    abstract fun error(): Throwable?

    companion object {
        fun create(loading: Boolean, categories: List<String>?, error: Throwable?): CategoryViewState {
            return AutoValue_CategoryViewState(loading, categories, error)
        }

        fun idle(): CategoryViewState {
            return create(false, null, null)
        }
    }
}