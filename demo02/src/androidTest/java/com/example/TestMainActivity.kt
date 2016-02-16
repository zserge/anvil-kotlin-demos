package com.example

import android.test.ActivityInstrumentationTestCase2
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.assertion.PositionAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.*

import android.graphics.Color
import android.widget.TextView
import android.view.View

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

    override fun setUp() {
        super.setUp()
        getActivity()
    }

    fun withTextColor(colorMatcher: Matcher<Int>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with color: ")
                colorMatcher.describeTo(description)
            }

            override fun matchesSafely(v: View): Boolean {
                return v is TextView && colorMatcher.matches(v.textColors.defaultColor)
            }
        };
    }

    fun testActivityShouldHaveThreeNames() {
        onView(withText("Alice")).check(isTopAlignedWith(withId(android.R.id.content)))
        onView(withText("Emily")).check(isBelow(withText("Alice")))
        onView(withText("Kate")).check(isBelow(withText("Emily")))
    }

    fun testClickChangesColor() {
        onView(withText("Alice")).check(matches(withTextColor(`is`(Color.WHITE))))
        onView(withText("Emily")).check(matches(withTextColor(`is`(Color.WHITE))))
        onView(withText("Kate")).check(matches(withTextColor(`is`(Color.WHITE))))

        onView(withText("Alice")).perform(click())

        onView(withText("Alice")).check(matches(withTextColor(`is`(Color.RED))))
        onView(withText("Emily")).check(matches(withTextColor(`is`(Color.WHITE))))
        onView(withText("Kate")).check(matches(withTextColor(`is`(Color.WHITE))))

        onView(withText("Emily")).perform(click())

        onView(withText("Alice")).check(matches(withTextColor(`is`(Color.WHITE))))
        onView(withText("Emily")).check(matches(withTextColor(`is`(Color.RED))))
        onView(withText("Kate")).check(matches(withTextColor(`is`(Color.WHITE))))
    }
}
