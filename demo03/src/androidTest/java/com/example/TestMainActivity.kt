package com.example

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.PositionAssertions.isBelow
import android.support.test.espresso.assertion.PositionAssertions.isTopAlignedWith
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.test.ActivityInstrumentationTestCase2

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

	override fun setUp() {
		super.setUp()
		getActivity()
	}

	public fun testActivityShouldHaveHeader() {
		onView(withText("Demo Header")).check(isTopAlignedWith(withId(android.R.id.content)))
	}

	public fun testActivityShouldHaveContent() {
		onView(withText("Content")).check(isBelow(withText("Demo Header")))
	}
}
