package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryActionProcessor @Inject constructor(private val repository: CategoryRepository) {

    private fun loadCategories(): ObservableTransformer<CategoryAction.LoadCategoriesAction, CategoryResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getCategories()
                        .flatMapObservable { Observable.just(it) }
                        .map { CategoryResult.success(it.list!!) }
                        .onErrorReturn { CategoryResult.error(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(CategoryResult.inFlight())
            }
        }
    }

    fun transformerFromAction(): ObservableTransformer<CategoryAction, CategoryResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(CategoryAction.LoadCategoriesAction::class.java).compose(loadCategories())
            }
        }
    }
}