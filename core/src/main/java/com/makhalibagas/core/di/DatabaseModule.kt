package com.makhalibagas.core.di

import android.content.Context
import androidx.room.Room
import com.makhalibagas.core.data.source.local.room.AnimeDao
import com.makhalibagas.core.data.source.local.room.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("makhalibagas".toCharArray() )
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(context, AnimeDatabase::class.java, "Anime.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideAnimeDao(db: AnimeDatabase): AnimeDao = db.animeDao()
}