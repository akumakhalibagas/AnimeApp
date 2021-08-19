package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(

	@SerializedName("relationships")
	val relationships: Relationships,

	@SerializedName("links")
	val links: Links,

	@SerializedName("attributes")
	val attributes: Attributes,

	@SerializedName("id")
	val id: String,

	@SerializedName("type")
	val type: String
) : Parcelable