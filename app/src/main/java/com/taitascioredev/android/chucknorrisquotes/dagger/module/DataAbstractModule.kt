package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.CategoryRepositoryImpl
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.JokeRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 5/01/18.
 */
@Module
abstract class DataAbstractModule {
    @Binds abstract fun provideJokeRepository(jokeRepositoryImpl: JokeRepositoryImpl): JokeRepository
    @Binds abstract fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}