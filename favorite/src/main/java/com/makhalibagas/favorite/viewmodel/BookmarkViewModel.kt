package com.makhalibagas.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.makhalibagas.core.domain.usecase.AnimeUseCase

class BookmarkViewModel(animeUseCase: AnimeUseCase) : ViewModel() {

    val bookmarkAnimes = animeUseCase.getBookmark().asLiveData()
    val bookmarkMangas = animeUseCase.getBookmarkManga().asLiveData()
}