package com.news.app.db
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(NewsEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}