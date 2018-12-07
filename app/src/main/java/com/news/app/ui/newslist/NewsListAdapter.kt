package com.news.app.ui.newslist

import android.arch.paging.PagedListAdapter
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.news.app.R
import com.news.app.di.GlideApp
import com.news.app.data.model.News
import com.news.app.ui.MainActivity
import com.news.app.ui.customview.NewsTextView
import com.news.app.ui.newsdetails.NewsDetailsFragment
import com.news.app.util.CommonUtil
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Adapter class for News List
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsListAdapter(activity: MainActivity) : PagedListAdapter<News, NewsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val activity: MainActivity = activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) as News
        holder.title.text = item.title
        holder.hours.text = CommonUtil.getHoursDiff(item.publishedAt).toString() + holder.hours.context.getString(R.string.hrsago)
        holder.content.text = item.content
        holder.from.text = item.author
        GlideApp.with(holder.itemView.context)
                .load(item.urlToImage)
                .into(holder.avatar)
        holder.container.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(NewsDetailsFragment::class.java.name, item)
            val newsDetailsFragment = NewsDetailsFragment.newInstance()
            newsDetailsFragment.arguments = bundle
            activity.replaceTheFragment(newsDetailsFragment, NewsDetailsFragment::class.java.name!!)
        })

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var container: LinearLayout = view.container
        var avatar: ImageView = view.avatar
        var title: NewsTextView = view.title
        var hours: NewsTextView = view.hours
        var content: NewsTextView = view.content
        var from: NewsTextView = view.from
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {

            override fun areItemsTheSame(oldUser: News,
                                         newUser: News): Boolean =
                    oldUser.title == newUser.title

            override fun areContentsTheSame(oldUser: News,
                                            newUser: News): Boolean =
                    oldUser == newUser
        }
    }
}