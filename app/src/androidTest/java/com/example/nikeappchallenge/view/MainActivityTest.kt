package com.example.nikeappchallenge.view

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.example.nikeappchallenge.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(InterruptedException::class)
    fun test_ui_is_displayed() {
        onView(withId(R.id.mi_searchView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_search_results))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerview_contents(){

        //Perform test input
        onView(withId(R.id.mi_searchView))
            .perform(typeText("jess"), pressKey(KeyEvent.KEYCODE_ENTER))

        //Wait for response
        Thread.sleep(1000)

        //Check that results loaded into the Recyclerview
        onView(withId(R.id.rv_search_results))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withId(R.id.cv_urbanDefinition))))
    }
}