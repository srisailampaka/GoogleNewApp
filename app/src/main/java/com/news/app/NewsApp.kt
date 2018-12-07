package com.news.app

import android.arch.persistence.room.Room
import com.facebook.stetho.Stetho
import com.news.app.db.AppDatabase
import com.news.app.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        var appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "news-db").allowMainThreadQueries().build()

        return DaggerApplicationComponent.builder().application(this).appDatabase(appDatabase).build()
    }


}