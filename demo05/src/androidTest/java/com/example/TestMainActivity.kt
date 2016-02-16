package com.example

import android.test.ActivityInstrumentationTestCase2
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.*

import android.view.View
import android.widget.TextView

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

	override fun setUp() {
		super.setUp()
		activity
	}

	fun withLineMultiplier(multiplierMatcher: Matcher<Float>): Matcher<View> {
		return object : TypeSafeMatcher<View>() {
			override fun describeTo(description: Description) {
				description.appendText("with line spacing multiplier: ")
				multiplierMatcher.describeTo(description)
			}

			override fun matchesSafely(v: View): Boolean {
				return v is TextView && multiplierMatcher.matches(v.lineSpacingMultiplier)
			}
		};
	}

	fun testActivityShouldHaveText() {
		onView(withText(startsWith("Lorem"))).check(matches(withLineMultiplier(`is`(2f))))
	}
}
