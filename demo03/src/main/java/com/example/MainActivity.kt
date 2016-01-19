package com.example

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.graphics.Color

import trikita.anvil.Anvil
import trikita.anvil.DSL.*

class MainActivity() : Activity() {

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)

		Anvil.mount(findViewById(android.R.id.content)) {
			layoutWithHeader("Demo Header") {
				textView {
					size(WRAP, WRAP)
					layoutGravity(CENTER)
					text("Content")
				}
			}
		}
	}

	fun layoutWithHeader(title: String, r: () -> Unit) {
		linearLayout {
			size(FILL, FILL)
			orientation(LinearLayout.VERTICAL)

			textView {
				size(FILL, dip(48))
				gravity(CENTER)
				text(title)
				textSize(28f)
				backgroundColor(Color.BLUE)
			}

			frameLayout {
				size(FILL, 0)
				weight(1f)

				r()
			}
		}
	}
}
