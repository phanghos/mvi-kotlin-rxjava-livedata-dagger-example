package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.google.auto.value.AutoValue
import com.taitascioredev.android.chucknorrisquotes.LceStatus
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviResult

/**
 * Created by rrtatasciore on 25/12/17.
 */
@AutoValue
abstract class CategoryResult : MviResult {

    abstract fun status(): LceStatus

    abstract fun categories(): List<String>?

    abstract fun error(): Throwable?

    companion object {
        fun success(categories: List<String>): CategoryResult {
            return AutoValue_CategoryResult(LceStatus.SUCCESS, categories, null)
        }

        fun error(error: Throwable): CategoryResult {
            return AutoValue_CategoryResult(LceStatus.ERROR, null, error)
        }

        fun inFlight(): CategoryResult {
            return AutoValue_CategoryResult(LceStatus.IN_FLIGHT, null, null)
        }
    }
}