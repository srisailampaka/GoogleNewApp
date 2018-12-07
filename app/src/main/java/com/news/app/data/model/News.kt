package com.news.app.data.model

import android.os.Parcel
import android.os.Parcelable
/**
 * This Model Class for to News
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
data class News(val title: String?, val content: String?, val publishedAt: String?, val urlToImage: String?, val author: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(publishedAt)
        parcel.writeString(urlToImage)
        parcel.writeString(author)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}