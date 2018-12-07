package com.news.app.ui.newsdetails

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.news.app.R
import com.news.app.di.GlideApp
import com.news.app.data.model.News
import com.news.app.ui.MainActivity
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.newsdetails_fragment.*
import javax.inject.Inject


private val TAG = NewsDetailsFragment::class.java.name

/**
 * This Fragment Class for to show the NewsDetails
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsDetailsFragment : DaggerFragment() {
    lateinit var news: News
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsDetailsViewModel
    var activityReference: MainActivity? = null
    companion object {
        fun newInstance() = NewsDetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.newsdetails_fragment, container, false)
        news = arguments!!.getParcelable(NewsDetailsFragment::class.java.name) as News
        activityReference?.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        activityReference?.getSupportActionBar()!!.title = getString(R.string.news_details)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsDetailsViewModel::class.java)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlideApp.with(this!!.context!!)
                .load(news.urlToImage)
                .into(avatar)
        title.text = news.title
        content.text = news.content
        publish.text = news.publishedAt
        from.text = news.author

    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            activityReference = context as MainActivity?
        }
    }
}