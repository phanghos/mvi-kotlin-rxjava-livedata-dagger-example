package com.taitascioredev.android.chucknorrisquotes

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)