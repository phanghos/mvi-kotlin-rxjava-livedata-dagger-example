package com.taitascioredev.android.chucknorrisquotes

import android.app.Activity
import com.github.ajalt.timberkt.Timber

/**
 * Created by rrtatasciore on 25/12/17.
 */
val Activity.app: ChuckNorrisApp
    get() = application as ChuckNorrisApp

fun Activity.log(msg: String) = Timber.e { msg }