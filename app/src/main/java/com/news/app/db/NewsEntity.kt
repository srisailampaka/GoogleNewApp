package com.news.app.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * This Entity Model Class for to create Sql table,column
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
@Entity(tableName = "news")
class NewsEntity(id:Int?,title: String?, content: String?, publishedAt: String?, urlToImage: String?, author: String?) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = id

    @ColumnInfo(name = "title")
    var title: String? = title

    @ColumnInfo(name = "content")
    var content: String? = content


    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = publishedAt

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = urlToImage

    @ColumnInfo(name = "author")
    val author: String? = author
}