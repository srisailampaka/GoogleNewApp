package com.news.app.ui.newslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import com.news.app.db.NewsDao
import com.news.app.data.model.News
import javax.inject.Inject


/**
 * This ViewModel Class for NewsList fragment
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsListViewModel @Inject constructor(private val itemDataSourceFactory: ItemDataSourceFactory, private val newsDao: NewsDao) : ViewModel() {

    val itemPagedList: LiveData<PagedList<News>>
    private val liveDataSource: LiveData<PageKeyedDataSource<Int, News>>
    var maxCountPerRow = 3
    val span = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (position % 7 == 0) {
                maxCountPerRow
            } else {
                1
            }
        }
    }

    init {
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    fun getSpanSize(orientation: Int): Int {
        maxCountPerRow = 3
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            maxCountPerRow = 2;
        }
        return maxCountPerRow
    }

    companion object {
        val PAGE_SIZE = 1000
    }

}