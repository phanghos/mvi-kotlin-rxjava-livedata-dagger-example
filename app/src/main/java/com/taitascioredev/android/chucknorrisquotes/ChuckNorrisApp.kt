package com.taitascioredev.android.chucknorrisquotes

import com.taitascioredev.android.chucknorrisquotes.dagger.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by rrtatasciore on 24/12/17.
 */
class ChuckNorrisApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        //Timber setup
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        return component!!
    }
}