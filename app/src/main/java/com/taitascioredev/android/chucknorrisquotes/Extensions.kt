package com.taitascioredev.android.chucknorrisquotes

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.github.ajalt.timberkt.Timber

/**
 * Created by rrtatasciore on 25/12/17.
 */
val Activity.app: ChuckNorrisApp
    get() = application as ChuckNorrisApp

fun Activity.log(msg: String) = Timber.e { msg }

fun AppCompatActivity.enableUpNavigation() {
    supportActionBar ?.let {
        it.setDisplayShowHomeEnabled(true)
        it.setDisplayHomeAsUpEnabled(true)
    }
}