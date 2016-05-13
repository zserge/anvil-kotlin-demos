package com.example

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import android.content.Context
import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout

import trikita.anvil.Anvil
import trikita.anvil.RenderableView
import trikita.anvil.DSL.*

class MainActivity() : Activity() {

	override fun onCreate(b: Bundle?) {
		super.onCreate(b)
		setContentView(Counter(this))
	}
}

class Counter(c: Context) : RenderableView(c) {
	var value = 0
	val exec = Executors.newSingleThreadScheduledExecutor()
	override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		exec.scheduleAtFixedRate({
			value++
			Anvil.render()
		}, 0, 100, TimeUnit.MILLISECONDS)
	}

	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		exec.shutdown()
	}

	override fun view() {
		linearLayout {
			orientation(LinearLayout.VERTICAL)
			textView {
				text("" + value)
			}
			button {
				text("Reset")
				onClick { v ->
					value = 0
				}
			}
		}
	}
}
