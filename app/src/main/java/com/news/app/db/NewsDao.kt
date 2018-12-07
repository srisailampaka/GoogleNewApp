package com.news.app.db

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: NewsEntity)

    @Update
    fun update(news: NewsEntity)


    @Query("Select * FROM news")
    fun getAllNews(): List<NewsEntity>

    @Query("DELETE FROM news")
    fun deleteAll()

}