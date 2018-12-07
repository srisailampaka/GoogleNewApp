package com.news.app.data

import com.news.app.data.model.NewsDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This Interface for handle all retrofit calls
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
interface NewsServices {
    @GET("top-headlines")
    fun getNewsList(@Query("country") country:String,@Query("category") category:String,@Query("pageSize") pageSize:String,@Query("page") page:String,@Query("apiKey") apiKey:String): Call<NewsDetails>

}