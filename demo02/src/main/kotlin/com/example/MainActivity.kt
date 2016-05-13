package com.example

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.graphics.Color

import trikita.anvil.Anvil
import trikita.anvil.DSL.*
import kotlin.collections.withIndex

class MainActivity() : Activity() {

	val names = arrayOf("Alice", "Emily", "Kate")
	var selected = -1

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)

		Anvil.mount(findViewById(android.R.id.content)) {
			linearLayout {
				orientation(LinearLayout.VERTICAL)

				for ((i, name) in names.withIndex()) {
					textView {
						text(name)
						textSize(48f)
						if (i == selected) {
							textColor(Color.RED)
						} else {
							textColor(Color.WHITE)
						}
						onClick { v ->
							selected = i
						}
					}
				}
			}
		}
	}
}
