package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PosterImage(

	@field:SerializedName("small")
	val small: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("tiny")
	val tiny: String,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("medium")
	val medium: String
) : Parcelable