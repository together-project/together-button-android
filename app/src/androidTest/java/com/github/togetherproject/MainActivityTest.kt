package com.github.togetherproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTogetherButtonVisible() {
        onView(withId(R.id.btnHelpMe)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTogetherDialogVisible() {
        onView(withId(R.id.btnHelpMe)).perform(click())

        onView(withId(R.id.bottomSheetConstraint)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTogetherDialogDisposedAfterBackButton() {
        onView(withId(R.id.btnHelpMe)).perform(click())
        onView(withId(R.id.bottomSheetConstraint)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.bottomSheetConstraint)).check(doesNotExist())
    }

    @Test
    fun test_isTogetherDialogDisposedAfterCloseButton() {
        onView(withId(R.id.btnHelpMe)).perform(click())
        onView(withId(R.id.bottomSheetConstraint)).check(matches(isDisplayed()))
        onView(withId(R.id.imgBtnClose)).perform(click())
        onView(withId(R.id.bottomSheetConstraint)).check(doesNotExist())
    }

    @Test
    fun test_isTogetherDialogDisposedAfterSwipeDown() {
        onView(withId(R.id.btnHelpMe)).perform(click())
        onView(withId(R.id.bottomSheetConstraint)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomSheetConstraint)).perform(swipeDown())
        Thread.sleep(1000)
        onView(withId(R.id.bottomSheetConstraint)).check(doesNotExist())
    }
}