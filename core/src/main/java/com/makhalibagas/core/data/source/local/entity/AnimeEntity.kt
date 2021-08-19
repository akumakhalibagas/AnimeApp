package com.makhalibagas.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_anime")
data class AnimeEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "autoId")
    val autoId: Int = 0,

    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "age")
    var age: String? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "decs")
    var decs: String? = null,

    @ColumnInfo(name = "key")
    var key: String? = null,

    @ColumnInfo(name = "isBookmark")
    var isBookmark: Boolean = false,

    @ColumnInfo(name = "isManga")
    var isManga: Boolean = false

) : Parcelable