package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.taitascioredev.android.chucknorrisquotes.data.db.CategoryConverter

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Entity(tableName = "categories")
@TypeConverters(CategoryConverter::class)
data class Categories(
        @TypeConverters(CategoryConverter::class) @ColumnInfo(name = "categories") var list: List<String>? = null,
        @PrimaryKey(autoGenerate = true) var id: Long = 0
)