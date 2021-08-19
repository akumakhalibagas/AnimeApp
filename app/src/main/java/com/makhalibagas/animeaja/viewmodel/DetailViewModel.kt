package com.makhalibagas.animeaja.viewmodel

import androidx.lifecycle.ViewModel
import com.makhalibagas.core.domain.model.Anime
import com.makhalibagas.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(private val animeUseCase: AnimeUseCase) : ViewModel() {

    fun setBookmark(anime: Anime, newStatus: Boolean) = animeUseCase.setBookmark(anime, newStatus)

}