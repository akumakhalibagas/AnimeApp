package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Titles(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("ja_jp")
	val jaJp: String,

	@field:SerializedName("en_jp")
	val enJp: String,

	@field:SerializedName("en_us")
	val enUs: String
) : Parcelable