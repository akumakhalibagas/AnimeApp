package com.makhalibagas.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Relationships(

	@field:SerializedName("animeCharacters")
	val animeCharacters: AnimeCharacters,

	@field:SerializedName("animeProductions")
	val animeProductions: AnimeProductions,

	@field:SerializedName("staff")
	val staff: Staff,

	@field:SerializedName("streamingLinks")
	val streamingLinks: StreamingLinks,

	@field:SerializedName("quotes")
	val quotes: Quotes,

	@field:SerializedName("characters")
	val characters: Characters,

	@field:SerializedName("castings")
	val castings: Castings,

	@field:SerializedName("mappings")
	val mappings: Mappings,

	@field:SerializedName("animeStaff")
	val animeStaff: AnimeStaff,

	@field:SerializedName("reviews")
	val reviews: Reviews,

	@field:SerializedName("installments")
	val installments: Installments,

	@field:SerializedName("genres")
	val genres: Genres,

	@field:SerializedName("mediaRelationships")
	val mediaRelationships: MediaRelationships,

	@field:SerializedName("categories")
	val categories: Categories,

	@field:SerializedName("productions")
	val productions: Productions,

	@field:SerializedName("episodes")
	val episodes: Episodes
) : Parcelable