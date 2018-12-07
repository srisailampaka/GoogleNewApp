package com.news.app.di


import com.news.app.ui.newslist.NewsListFragment
import com.news.app.ui.newsdetails.NewsDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeNewsListFragmentInjector(): NewsListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeNewsDetailFragmentInjector(): NewsDetailsFragment
}