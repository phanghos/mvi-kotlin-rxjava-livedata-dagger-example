package com.taitascioredev.android.chucknorrisquotes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.taitascioredev.android.chucknorrisquotes.feature.categories.Categories

/**
 * Created by rrtatasciore on 31/12/17.
 */
@Database(entities = arrayOf(Categories::class), version = 1, exportSchema = false)
@TypeConverters(CategoryConverter::class)
abstract class ChuckNorrisDb : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}