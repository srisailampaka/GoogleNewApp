package com.news.app.data.repository


import com.news.app.data.NewsServices
import com.news.app.data.model.NewsDetails
import retrofit2.Call

/**
 * Implementation class for News Repository
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsRepositoryImpl(private val service: NewsServices) : NewsRepository {

    override fun getNewsList(country: String, category:String, pageSize:String , page:String,apiKey: String): Call<NewsDetails> {
        return service.getNewsList(country, category,pageSize,page,apiKey)
    }

}