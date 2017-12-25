package com.taitascioredev.android.chucknorrisquotes

import android.app.Application
import com.taitascioredev.android.chucknorrisquotes.dagger.component.AppComponent
import com.taitascioredev.android.chucknorrisquotes.dagger.component.DaggerAppComponent
import com.taitascioredev.android.chucknorrisquotes.dagger.module.NetModule
import timber.log.Timber

/**
 * Created by rrtatasciore on 24/12/17.
 */
class ChuckNorrisApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().netModule(NetModule()).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        //Timber setup
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}