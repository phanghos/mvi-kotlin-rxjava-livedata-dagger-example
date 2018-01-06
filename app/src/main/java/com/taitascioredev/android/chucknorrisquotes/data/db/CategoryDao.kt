package com.taitascioredev.android.chucknorrisquotes.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.taitascioredev.android.chucknorrisquotes.model.Categories

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Dao
interface CategoryDao {

    @Insert(onConflict = REPLACE)
    fun insertCategories(categories: Categories)

    @Query("SELECT * FROM categories")
    fun getCategories(): Categories
}