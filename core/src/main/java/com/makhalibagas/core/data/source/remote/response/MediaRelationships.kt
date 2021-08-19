package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaRelationships(

	@field:SerializedName("links")
	val links: Links
) : Parcelable