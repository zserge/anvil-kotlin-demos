package com.example

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.test.ActivityInstrumentationTestCase2
import android.widget.TextView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

	override fun setUp() {
		super.setUp()
		getActivity()
	}

	public fun testActivityShouldHaveText() {
		onView(allOf(withParent(withId(android.R.id.content)), instanceOf(TextView::class.java)))
			.check(matches(withText("Hello!")))
	}
}
