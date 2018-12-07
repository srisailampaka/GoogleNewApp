package com.news.app

import android.content.Context
import com.news.app.data.repository.NewsRepository
import com.news.app.db.NewsDao
import com.news.app.ui.newslist.ItemDataSource
import com.news.app.ui.newslist.ItemDataSourceFactory
import com.news.app.ui.newslist.NewsListViewModel
import com.news.app.util.mock
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewsListViewModellTest {

    @Mock private lateinit var repo :NewsRepository
    @Mock private lateinit var context :Context
    @Mock private lateinit var newsDao: NewsDao

    private lateinit var newsListViewModel:NewsListViewModel

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        var itemDataSource = ItemDataSource(repo,context,newsDao);
        var itemDataSourceFactory  =  ItemDataSourceFactory(itemDataSource)
        newsListViewModel = NewsListViewModel(itemDataSourceFactory,newsDao)
    }
    @Test
    fun testNull() {
        Assert.assertThat(newsListViewModel.itemPagedList, IsNull.notNullValue())
    }

    @Test
    fun testGetSpanSize() {
        Assert.assertEquals(newsListViewModel.getSpanSize(1),2)
        Assert.assertEquals(newsListViewModel.getSpanSize(0),3)
    }


}