package com.news.app.di

import com.news.app.data.NewsServices
import com.news.app.data.repository.NewsRepository
import com.news.app.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsServices): NewsRepository = NewsRepositoryImpl(newsServices)
}