package com.news.app.di

import android.app.Application
import com.news.app.db.AppDatabase
import com.news.app.NewsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        MainModule::class,
        RepositoryModule::class,
        RoomModule::class,
        ViewModelModule::class
))
interface ApplicationComponent:AndroidInjector<DaggerApplication>  {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun appDatabase(appDatabase: AppDatabase): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: NewsApp)
    override fun inject(instance: DaggerApplication)

}