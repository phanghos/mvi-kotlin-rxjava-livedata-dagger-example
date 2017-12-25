package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeMapper
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.CategoryRepositoryImpl
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.JokeRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 24/12/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideJokeRepository(service: ChuckNorrisService, mapper: JokeMapper): JokeRepository {
        return JokeRepositoryImpl(service, mapper)
    }

    @Provides
    @Singleton
    fun provideJokeMapper(): JokeMapper = JokeMapper()

    @Provides
    @Singleton
    fun provideCategoryRepository(service: ChuckNorrisService): CategoryRepository {
        return CategoryRepositoryImpl(service)
    }
}