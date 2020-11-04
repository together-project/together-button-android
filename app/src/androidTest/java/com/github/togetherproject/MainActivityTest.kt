package com.github.togetherproject

import android.Manifest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.CALL_PHONE)

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private fun setAndCheckTogetherButtonFragmentVisible() {
        onView(withId(R.id.btnHelpMe)).perform(click())
        onView(withId(R.id.togetherBottomSheetLayout)).check(matches(isDisplayed()))
    }

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
        setAndCheckTogetherButtonFragmentVisible()
    }

    @Test
    fun test_isTogetherDialogDisposedAfterBackButton() {
        setAndCheckTogetherButtonFragmentVisible()
        pressBack()
        onView(withId(R.id.togetherBottomSheetLayout)).check(doesNotExist())
    }

    @Test
    fun test_isTogetherDialogDisposedAfterCloseButton() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.img_close)).perform(click())
        onView(withId(R.id.togetherBottomSheetLayout)).check(doesNotExist())
    }

    @Test
    fun test_isTogetherDialogDisposedAfterSwipeDown() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.togetherBottomSheetLayout)).perform(swipeDown())
        Thread.sleep(1000)
        onView(withId(R.id.togetherBottomSheetLayout)).check(doesNotExist())
    }

    @Test
    fun test_isHelpFragmentShowingAfterButtonClick() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.btnCallForHelp)).perform(click())
        onView(withId(R.id.askForHelpLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_wasReturnedFromHelpFragmentAfterBackArrow() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.btnCallForHelp)).perform(click())
        onView(withId(R.id.askForHelpLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.img_back_from_call)).perform(click())
        onView(withId(R.id.img_logo_from_home)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isServicesFragmentShowingAfterButtonClick() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.btnCallForHelp)).perform(click())
        onView(withId(R.id.askForHelpLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.btnServices)).perform(click())
        onView(withId(R.id.servicesLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_wasReturnedFromServicesFragmentAfterBackArrow() {
        setAndCheckTogetherButtonFragmentVisible()
        onView(withId(R.id.btnCallForHelp)).perform(click())
        onView(withId(R.id.askForHelpLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.btnServices)).perform(click())
        onView(withId(R.id.servicesLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.img_back_from_services)).perform(click())
        onView(withId(R.id.askForHelpLayout)).check(matches(isDisplayed()))
    }
}