package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dimensions(

	@field:SerializedName("small")
	val small: Small,

	@field:SerializedName("large")
	val large: Large,

	@field:SerializedName("tiny")
	val tiny: Tiny,

	@field:SerializedName("medium")
	val medium: Medium
) : Parcelable