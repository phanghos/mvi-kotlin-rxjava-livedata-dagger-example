package com.taitascioredev.android.chucknorrisquotes.dagger.component

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.dagger.module.CategoryModule
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoriesActivity
import dagger.Subcomponent

/**
 * Created by rrtatasciore on 25/12/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(categoriesActivity: CategoriesActivity)
}