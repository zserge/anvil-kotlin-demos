# Anvil demos

This is a collection of minimal apps to show various aspects of
[Anvil](https://github.com/zserge/anvil). All demos are written in Kotlin,
which is a natural language of choice for Android platform these days. However,
Anvil itself does not require Kotlin and can be used with Java 6 or newer.

These demos are purposely written in a simple and clear style.
You will find no difficulty in following them to learn the powerful library.
 
## Related projects

* [Anvil](https://github.com/zserge/anvil)
* [More Anvil examples](https://github.com/zserge/anvil-examples)
* [Inspired by react-demos](https://github.com/ruanyf/react-demos)

## How to use

Fetch the code and use gradle to install any of the demos:

	$ git clone git@github.com:zserge/anvil-kotlin-demos.git
	$ cd anvil-kotlin-demos
	$ ./gradlew :demo01:installDebug

If you want to run tests for the demos:
	
	$ ./gradlew :demo01:connectedCheck

Each demo consists of a single Kotlin file
(`demoNN/src/main/java/com/example/MainActivity.kt`), other project files are
shared across all the demos.

# Index

# Demo 1: Rendering a static layout ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo01/src/main/java/com/example/MainActivity.kt))

Anvil DSL syntax is very similar to
[Anko](https://github.com/Kotlin/ank://github.com/Kotlin/anko), which in its
turn follows the structure of traditional android XML layouts.

```kotlin
Anvil.mount(findViewById(android.R.id.content)) {
	textView {
		text("Hello!")
	}
}
```

Every view starts with a view class name and follows by a pair curly braces (a
_renderable_). Inside the renderable you define view attributes and other
nested views.

If you want your renderable to be displayed - you have to mount it to some view
attaching the described layout into the specified view. This is similar to
`setContentView()` or `LayoutInflater.inflate()` in traditional Android.

# Demo 02: Binding data and event listeners ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo02/src/main/java/com/example/MainActivity.kt))

Anvil renderables are simple anonymous functions (lambdas), so unlike XMLs here
you can write any kind of code inside (variables, conditionals, loops, other
lambdas etc).

```kotlin
val names = arrayOf("Alice", "Emily", "Kate")
var selected = -1

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
```

If you use a variable as an attribute value and later change the variable -
your layout will be updated, but only the changed view attributes will be
actually modified (like in [Incremental DOM](http://google.github.io/incremental-dom/)).

In the example above the colors of the text views is be changed automatically
as you click on the names, because the color is bound to the `selected`
variable, which is modified inside the click listeners bound to the text views.

# Demo 03: Refactoring, styles and components ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo03/src/main/java/com/example/MainActivity.kt))

Full power of your programming language makes is very easy to refactor your
code or to share common styles across various parts of your UI.

For example, here is a custom layout with a header on top and centered content below it.
This simple function can be reused with different header titles and content:

```kotlin
layoutWithHeader("Demo Header") {
	textView {
		size(WRAP, WRAP)
		layoutGravity(CENTER)
		text("Content")
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
```

A common technique is to pass a renderable (`r`) as a last argument to your
custom component function to override its behavior.

# Demo 04: Adapters ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo04/src/main/java/com/example/MainActivity.kt))

While it's now to render a collection of data into a number of views with a
simple loop, it's still recommended to uses Adapters if the size of your data
is unknown and can be large.

Anvil provides a special helper to create your own adapter of renderables,
which handles their lifecycle automatically.

```kotlin
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
```

# Demo 05: Accessing views directly ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo05/src/main/java/com/example/MainActivity.kt))

Sometimes you have to deal with views/attributes not covered with Anvil DSL.
Most often it will be the custom views or rarely used attribute setters.

`Anvil.currentView()` returns the instance of the real view being currently
rendered. You should use that if you need to call view methods directly. This
might be also helpful if you need to keep a reference to the view to use it
from other parts of code (like `findViewById`), but you should not really need
this in Anvil.

```kotlin
val loremIpsumText = "Lorem ipsum dolor sit amet..."
textView {
	size(FILL, FILL)
	text(loremIpsumText)
	init {
		val tv: TextView = Anvil.currentView()
		tv.setLineSpacing(0.5f, 2f)
	}
}
```

If you want to ensure that you code is executed once you can put it into the
`init {}` block. This is helpful for setting up your custom views.

# Demo 06: XML layouts ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo06/src/main/java/com/example/MainActivity.kt))

Sometimes you have to use XML. A good example is `style` attribute which can
not be set in code. Also, some poorly designed 3rd-party views don't provide a
full Java API taking all parameters as `AttributeSet` from XML.

But this should not stop you from using such views in Anvil:

```kotlin
val names = arrayOf("Alice", "Emily", "Kate")

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
```

`xml` inflates a layout and attaches the resulting view into the current
renderable as if you wrote that layout with Anvil DSL.

`withId` allows to find an existing view (may be on any level of hierarchy, but
must be inside a given parent renderable node) and allows you to override its
attributes, bind data and event listeners.

# Demo 07: RenderableView ([source](https://github.com/zserge/anvil-kotlin-demos/blob/master/demo07/src/main/java/com/example/MainActivity.kt))

While renderable functions are easy to combine and manage, sometimes you
need self-contained stateful components.

You can extend from RenderableView and override the `view()` method to declare
the layout. Renderable view acts as a simple renderable function, except you
don't have to mount/unmount it - instead you should add/remove the view itself.

You may override `onAttachedToWindow` and `onDetachedFromWindow` to handle your
view lifecycle (e.g. to start/stop timers or background tasks).

```kotlin
setContentView(Counter(this))

...

class Counter(c: Context): RenderableView(c) {
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
				text(""+value)
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
```

This is especially helpful if you support the ideas described in
["Advocating against fragments"](https://corner.squareup.com/2014/10/advocating-against-android-fragments.html).

You can keep your screens as separate RenderableViews.  Each screen then acts
as a standalone controller for the view defined in its `view()` method. You may
use libraries like [Flow](https://github.com/square/flow) or
[AndroidRouter](https://github.com/trikita/android-router) to do navigation
between your screen.

# Demo 08: Different screen support (TODO)

# Demo 09: Anvil extensions (TODO)

