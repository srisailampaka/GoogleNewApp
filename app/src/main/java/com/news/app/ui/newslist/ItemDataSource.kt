package com.news.app.ui.newslist

import android.arch.paging.PageKeyedDataSource
import android.content.Context
import com.news.app.db.NewsDao
import com.news.app.db.NewsEntity
import com.news.app.data.model.News
import com.news.app.data.repository.NewsRepository
import com.news.app.util.CommonUtil
import java.io.IOException
import javax.inject.Inject

/**
 *  DataSource class for Paging the api details
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
public class ItemDataSource @Inject constructor(private val repo: NewsRepository, val context: Context, private val newsDao: NewsDao) : PageKeyedDataSource<Int, News>() {

    private val FIRST_PAGE = 1
    private val INTIAL_COUNT = 21
    private val NEXT_COUNT = 20
    private val COUNTRY = "de"
    private val CATEORY = "business"
    private val API_KEY = "bc7fe6f6acf048faaef232fe45980a2e"

    var count: Int = 0
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        callAPI(FIRST_PAGE, INTIAL_COUNT) { repos, next ->
            callback.onResult(repos, null, next)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        if (CommonUtil.isNetworkStatusAvailable(context)) {
            callAPI(params.key, NEXT_COUNT) { repos, next ->
                callback.onResult(repos, next)
            }
        }
    }

    private fun callAPI(page: Int, perPage: Int, callback: (repos: List<News>, next: Int?) -> Unit) {


        try {
            if (CommonUtil.isNetworkStatusAvailable(context)) {
                val response = repo.getNewsList(COUNTRY, CATEORY, perPage.toString(), page.toString(),
                        API_KEY).execute()
                response.body()?.let {
                    var next: Int? = null
                    next = page + 1
                    callback(it.articles, next)
                    for (i in 0 until it.articles.size) {
                        val item = it.articles[i]
                        newsDao.insert(NewsEntity(count + i, item?.title, item?.content, item?.publishedAt, item?.urlToImage, item?.author))
                    }
                    count = it.articles.size + count
                }
            } else {
                newsDao.getAllNews()?.let {
                    val list = ArrayList<News>()
                    it.forEach {
                        list.add(News(it.title, it.content, it.publishedAt, it.urlToImage, it.author))
                    }
                    var next: Int? = null
                    next = 1
                    callback(list, next)
                }
            }


        } catch (e: IOException) {
            // Timber.w(e)
        }
    }

}