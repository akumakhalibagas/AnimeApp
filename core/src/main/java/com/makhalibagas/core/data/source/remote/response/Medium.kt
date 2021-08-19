package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medium(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("height")
	val height: Int
) : Parcelable