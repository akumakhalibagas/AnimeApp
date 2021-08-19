package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoverImage(

	@SerializedName("small")
	val small: String,

	@SerializedName("original")
	val original: String,

	@SerializedName("large")
	val large: String,

	@SerializedName("tiny")
	val tiny: String,

	@SerializedName("meta")
	val meta: Meta
) : Parcelable