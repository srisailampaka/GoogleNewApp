package com.news.app.ui

import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.news.app.R
import com.news.app.ui.newslist.NewsListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * This Activity Class for Main Screen
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class MainActivity : DaggerAppCompatActivity() {
    var espressoTestIdlingResource = CountingIdlingResource("Network_Call")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceTheFragment(NewsListFragment.newInstance(), NewsListFragment::class.java.name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish();
        } else {
            super.onBackPressed()
        }

    }

    /*
    This method for replace the fragment to container
   */
    fun replaceTheFragment(fragment: Fragment, backStackText: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(backStackText)
                .commit()
    }
}