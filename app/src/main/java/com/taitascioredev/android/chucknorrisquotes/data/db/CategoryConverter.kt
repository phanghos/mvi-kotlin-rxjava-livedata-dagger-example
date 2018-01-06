package com.taitascioredev.android.chucknorrisquotes.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.taitascioredev.android.chucknorrisquotes.fromJson

/**
 * Created by rrtatasciore on 1/01/18.
 */
class CategoryConverter {

    @TypeConverter
    fun fromListToString(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(json: String): List<String> = Gson().fromJson<List<String>>(json)
}