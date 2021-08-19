package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("dimensions")
	val dimensions: Dimensions
) : Parcelable