package com.example

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.test.ActivityInstrumentationTestCase2
import org.hamcrest.CoreMatchers.`is`

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

	override fun setUp() {
		super.setUp()
		activity
	}

	fun testActivityShouldHave100Items() {
		onData(`is`("Item #0")).check(matches(isDisplayed()))
		onData(`is`("Item #99")).check(matches(isDisplayed()))
	}
}
