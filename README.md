# Lazy List Sample Project

A demo codebase that shows how to create build and manage Lazy Lists with Compose more effectively.

Full Lazy List documentation for Jetpack Compose can be
found [here](https://developer.android.com/jetpack/compose/lists)

This project introduces
the [GenericLazyItem.kt](app%2Fsrc%2Fmain%2Fjava%2Fcom%2Fteegarcs%2Flazy%2Fcore%2FGenericLazyItem.kt)
which can be used as a base class for Data Classes that represent the state of various Items within
a List. It provides functions that should be implemented when using in combo
with [GenericList.kt](app%2Fsrc%2Fmain%2Fjava%2Fcom%2Fteegarcs%2Flazy%2Fcore%2FGenericList.kt)
.genericList() function.

The genericList function is a function within a Lazy List scope such as a LazyColumn or LazyRow.
This abstracts away the general logic used in combination with GenericLazyItem objects. This
function will invoke the BuildHeaderItems functions where it should and the BuildItem functions
for each item in the list.

It takes in two params:

- items - The GenericLazyItems that you would like to be rendered with the global rendering rules
- processIntent a function you wish to carry back item and item header interactions with.

The GenericList function and the GenericLazyItem when used together makes your Lazy use cases
much easier to manage and creates a pattern to re-use Items across many different screens.
Furthermore it abstracts away logic for building sticky headers and implementing key Lazy features
that help list performance.

Sample Use:

```kotlin
val listState = rememberLazyListState()
LazyColumn(state = listState) {
    genericList(state.lazyItems) { processIntent(MainIntent.ItemSelected(it)) }
}
```