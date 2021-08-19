package com.makhalibagas.core.utils

import com.makhalibagas.core.data.source.local.entity.AnimeEntity
import com.makhalibagas.core.data.source.remote.response.DataItem
import com.makhalibagas.core.domain.model.Anime

object DataMapper {

    fun mapResponseToEntities(input: List<DataItem>): List<AnimeEntity> {
        val animes = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                id = it.id.toInt(),
                image = it.attributes.posterImage.original,
                title = it.attributes.titles.en,
                age = it.attributes.ageRatingGuide,
                status = it.attributes.status,
                decs = it.attributes.description,
                key = it.attributes.youtubeVideoId,
                isBookmark = false,
                isManga = false

            )
            animes.add(anime)
        }
        return animes
    }

    fun mapResponseMangaToEntities(input: List<DataItem>): List<AnimeEntity> {
        val animes = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                id = it.id.toInt(),
                image = it.attributes.posterImage.original,
                title = it.attributes.titles.enJp,
                age = it.attributes.ageRatingGuide,
                status = it.attributes.status,
                decs = it.attributes.description,
                key = it.attributes.youtubeVideoId,
                isBookmark = false,
                isManga = true

            )
            animes.add(anime)
        }
        return animes
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                autoId = it.autoId,
                id = it.id,
                image = it.image,
                title = it.title,
                age = it.age,
                status = it.status,
                decs = it.decs,
                key = it.key,
                isBookmark = it.isBookmark,
                isManga = it.isManga
            )
        }

    fun mapDomainToEntities(input: Anime) =
        AnimeEntity(
            autoId = input.autoId,
            id = input.id,
            image = input.image,
            title = input.title,
            age = input.age,
            status = input.status,
            decs = input.decs,
            key = input.key,
            isBookmark = input.isBookmark,
            isManga = input.isManga
        )

}