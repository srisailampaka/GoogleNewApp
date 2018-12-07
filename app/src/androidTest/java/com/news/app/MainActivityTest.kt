package com.news.app

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.news.app.ui.MainActivity
import com.news.app.ui.newslist.NewsListAdapter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class MainActivityTest {
    @Rule
    @JvmField
    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java)

    @Test
    fun showListScreenTest() {
        IdlingRegistry.getInstance().register(mActivityRule.activity.espressoTestIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        IdlingRegistry.getInstance().unregister(mActivityRule.activity.espressoTestIdlingResource)
    }

    @Test
    fun showDetailsScreenTest() {
        IdlingRegistry.getInstance().register(mActivityRule.activity.espressoTestIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.ViewHolder>(1, ViewActions.click()))
        IdlingRegistry.getInstance().unregister(mActivityRule.activity.espressoTestIdlingResource)
        IdlingRegistry.getInstance().register(mActivityRule.activity.espressoTestIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.avatar)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        IdlingRegistry.getInstance().unregister(mActivityRule.activity.espressoTestIdlingResource)
    }
}
