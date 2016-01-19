package com.example

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.test.ActivityInstrumentationTestCase2
import android.widget.Button
import org.hamcrest.CoreMatchers.instanceOf

class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {

	override fun setUp() {
		super.setUp()
		getActivity()
	}

	public fun testActivityButtonCanBeClicked() {
		onView(instanceOf(Button::class.java)).perform(click())
	}
}
