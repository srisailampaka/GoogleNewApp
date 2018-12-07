package com.news.app.data.repository

import com.news.app.data.model.NewsDetails
import retrofit2.Call
interface NewsRepository {
    fun getNewsList(country: String, category: String,pageSize:String, page: String, apiKey: String): Call<NewsDetails>

}