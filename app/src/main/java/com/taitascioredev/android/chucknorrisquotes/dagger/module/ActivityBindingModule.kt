package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoriesActivity
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesActivity
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.RandomJokeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(RandomJokeModule::class))
    abstract fun jokeActivity(): RandomJokeActivity

    @ContributesAndroidInjector(modules = arrayOf(CategoryModule::class))
    abstract fun categoriesActivity(): CategoriesActivity

    @ContributesAndroidInjector(modules = arrayOf(JokesModule::class))
    abstract fun jokesActivity(): JokesActivity
}