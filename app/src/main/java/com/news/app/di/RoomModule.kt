package com.news.app.di

import javax.inject.Singleton
import dagger.Provides
import com.news.app.db.AppDatabase
import com.news.app.db.NewsDao
import dagger.Module

@Module
class RoomModule {
    @Singleton
    @Provides
    fun providesArticleDao(appDb: AppDatabase): NewsDao = appDb.newsDao()
}