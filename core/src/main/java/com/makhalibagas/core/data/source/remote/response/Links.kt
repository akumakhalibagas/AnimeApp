package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("last")
	val last: String,

	@field:SerializedName("first")
	val first: String,

	@field:SerializedName("self")
	val self: String,

	@field:SerializedName("related")
	val related: String
) : Parcelable