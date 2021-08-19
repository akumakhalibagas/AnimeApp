package com.makhalibagas.animeaja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.makhalibagas.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class AnimeViewModel @Inject constructor(animeUseCase: AnimeUseCase) : ViewModel() {

    val animes = animeUseCase.getAnime().asLiveData()
    val manga = animeUseCase.getManga().asLiveData()


}