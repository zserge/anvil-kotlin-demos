package com.example

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import trikita.anvil.Anvil
import trikita.anvil.DSL.*

class MainActivity() : Activity() {

	val names = arrayOf("Alice", "Emily", "Kate")

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)

		Anvil.mount(findViewById(android.R.id.content)) {
			linearLayout {
				orientation(LinearLayout.VERTICAL)

				for ((i, name) in names.withIndex()) {
					xml(android.R.layout.simple_list_item_1) {
						withId(android.R.id.text1) {
							text(name)
						}
					}
				}
			}
		}
	}
}
