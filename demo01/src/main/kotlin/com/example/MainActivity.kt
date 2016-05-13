package com.example

import android.app.Activity
import android.os.Bundle
import trikita.anvil.Anvil
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView

class MainActivity() : Activity() {
	override fun onCreate(b: Bundle?) {
		super.onCreate(b)
		Anvil.mount(findViewById(android.R.id.content)) {
			textView {
				text("Hello!")
			}
		}
	}
}
