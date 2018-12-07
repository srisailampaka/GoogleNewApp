package com.news.app.ui.newslist

import android.arch.paging.DataSource
import com.news.app.data.model.News
import android.arch.paging.PageKeyedDataSource
import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 *  Factory class for Paging the api details
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */

class ItemDataSourceFactory @Inject constructor(private val itemDataSource: ItemDataSource) : DataSource.Factory<Int, News>() {
    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, News>>()


    override fun create(): DataSource<Int, News> {
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, News>> {
        return itemLiveDataSource
    }
}