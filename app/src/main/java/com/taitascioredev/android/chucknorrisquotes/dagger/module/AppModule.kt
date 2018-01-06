package com.taitascioredev.android.chucknorrisquotes.dagger.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.taitascioredev.android.chucknorrisquotes.data.db.ChuckNorrisDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 2/01/18.
 */
@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideChuckNorrisDb(context: Context): ChuckNorrisDb {
        return Room.databaseBuilder(context, ChuckNorrisDb::class.java, "chuck-norris-db")
                .allowMainThreadQueries()
                .build()
    }
}