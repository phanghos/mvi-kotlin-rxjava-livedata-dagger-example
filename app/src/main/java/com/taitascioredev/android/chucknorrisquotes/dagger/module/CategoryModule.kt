package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoriesActivity
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoryActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoryStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoryViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 25/12/17.
 */
@Module
class CategoryModule {

    @Provides
    @ActivityScope
    fun provideCategoryViewModelFactory(actionProcessor: CategoryActionProcessor, stateReducer: CategoryStateReducer): CategoryViewModelFactory {
        return CategoryViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides
    @ActivityScope
    fun provideCategoryActionProcessor(repository: CategoryRepository): CategoryActionProcessor {
        return CategoryActionProcessor(repository)
    }

    @Provides
    @ActivityScope
    fun provideCategoryStateReducer(): CategoryStateReducer = CategoryStateReducer()
}