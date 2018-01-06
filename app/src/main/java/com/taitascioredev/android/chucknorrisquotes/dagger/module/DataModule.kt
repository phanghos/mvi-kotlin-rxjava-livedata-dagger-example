package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.db.ChuckNorrisDb
import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeMapper
import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeQueryMapper
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.data.repository.datasource.CategoryDataStoreFactory
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.CategoryRepositoryImpl
import com.taitascioredev.android.chucknorrisquotes.data.repository.impl.JokeRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Module(includes = arrayOf(DataAbstractModule::class))
class DataModule {

    @Provides
    @ActivityScope
    fun provideJokeRepositoryImpl(service: ChuckNorrisService, jokeMapper: JokeMapper, jokeQueryMapper: JokeQueryMapper): JokeRepositoryImpl {
        return JokeRepositoryImpl(service, jokeMapper, jokeQueryMapper)
    }

    @Provides
    @ActivityScope
    fun provideCategoryRepositoryImpl(dataStoreFactory: CategoryDataStoreFactory): CategoryRepositoryImpl {
        return CategoryRepositoryImpl(dataStoreFactory)
    }

    @Provides
    @ActivityScope
    fun provideCategoryDataStoreFactory(service: ChuckNorrisService, db: ChuckNorrisDb): CategoryDataStoreFactory {
        return CategoryDataStoreFactory(service, db)
    }
}