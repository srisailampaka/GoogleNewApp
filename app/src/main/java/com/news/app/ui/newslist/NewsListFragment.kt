package com.news.app.ui.newslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.news.app.R
import com.news.app.data.model.News
import com.news.app.ui.MainActivity
import com.news.app.util.ProgressDialog
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.news_list_fragment.*
import javax.inject.Inject
import android.app.Activity


private val TAG = NewsListFragment::class.java.name

/**
 * This Fragment Class for to show the News List
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsListFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsListViewModel

    var activityReference: MainActivity? = null

    companion object {
        fun newInstance() = NewsListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.news_list_fragment, container, false)
        activityReference?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activityReference?.supportActionBar?.title = getString(R.string.newslist)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsListAdapter(activityReference as MainActivity)
        recyclerview.layoutManager = LinearLayoutManager(activityReference)
        recyclerview.adapter = adapter
        ProgressDialog.showProgressDialog(context)
        activityReference?.espressoTestIdlingResource?.increment()

        viewModel.itemPagedList.observe(this, Observer<PagedList<News>> { items ->
            adapter.submitList(items)
            ProgressDialog.dismissProgressDialog()
            (activityReference as MainActivity).espressoTestIdlingResource.decrement()
        })
        val manager = GridLayoutManager(context, viewModel.getSpanSize(resources.configuration.orientation)) // MAX NUMBER OF SPACES
        manager.spanSizeLookup = viewModel.span

        recyclerview.layoutManager = manager

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            activityReference = context as MainActivity?
        }
    }

}