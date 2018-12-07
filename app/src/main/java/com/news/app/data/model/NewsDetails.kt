package com.news.app.data.model

import com.news.app.data.model.News
/**
 * This Model Class for to NewsDetails
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
data class NewsDetails(val articles: List<News>, val totalResults: Int, val status: String) {
}