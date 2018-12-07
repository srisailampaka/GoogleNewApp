package com.news.app.util

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * This class to implement the common functions for the app
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class CommonUtil {
    companion object {

        fun isNetworkStatusAvailable(context: Context?): Boolean {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            connectivityManager?.let {
                it.activeNetworkInfo?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }

        fun getHoursDiff(date: String?): Long {
            val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val dtf = SimpleDateFormat(pattern)
            val dateTime = dtf.parse(date)
            val curentDate = Date()
            val diff = curentDate.getTime() - dateTime.getTime();
            return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)
        }
        fun getRandomNumber(): Long {
            return (Math.random() * (100000 - 0 + 1) + 0).toLong()
        }
    }


}