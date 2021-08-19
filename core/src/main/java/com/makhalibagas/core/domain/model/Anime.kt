package com.makhalibagas.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(

    var autoId: Int,
    var id: Int,
    var image: String? = null,
    var title: String? = null,
    var age: String? = null,
    var status: String? = null,
    var decs: String? = null,
    var key: String? = null,
    var isBookmark: Boolean = false,
    var isManga: Boolean = false

) : Parcelable