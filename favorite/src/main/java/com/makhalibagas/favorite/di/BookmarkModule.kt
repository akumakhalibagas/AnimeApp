package com.makhalibagas.favorite.di

import android.content.Context
import com.makhalibagas.animeaja.di.BookmarkModuleDependencies
import com.makhalibagas.favorite.BookmarkFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [BookmarkModuleDependencies::class])
interface BookmarkModule {

    fun inject(bookmarkFragment: BookmarkFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(bookmarkModuleDependencies: BookmarkModuleDependencies): Builder
        fun build(): BookmarkModule
    }
}