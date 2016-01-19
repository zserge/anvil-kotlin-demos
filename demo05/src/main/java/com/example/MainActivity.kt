package com.example

import android.app.Activity
import android.os.Bundle
import android.widget.TextView;

import trikita.anvil.Anvil
import trikita.anvil.DSL.*

class MainActivity() : Activity() {

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)

		val loremIpsumText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
		Anvil.mount(findViewById(android.R.id.content)) {
			textView {
				size(FILL, FILL)
				text(loremIpsumText)
				init {
					val tv: TextView = Anvil.currentView()
					tv.setLineSpacing(0.5f, 2f)
				}
			}
		}
	}
}
