package com.example

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import trikita.anvil.Anvil
import trikita.anvil.DSL.*
import trikita.anvil.RenderableAdapter
import kotlin.collections.asList

class MainActivity() : Activity() {

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)

		val items = Array(100, {i -> "Item #" + i}).asList()

		val demoAdapter = RenderableAdapter.withItems(items, { pos, item ->
			textView {
				text(item)
				textColor(Color.HSVToColor(floatArrayOf(pos*3.6f, 1f, 1f)))
			}
		})

		Anvil.mount(findViewById(android.R.id.content)) {
			listView {
				size(FILL, FILL)
				adapter(demoAdapter)
				onItemClick { av, v, pos, id ->
					Toast.makeText(this, "Clicked on item #" + pos, Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
