package com.teegarcs.lazy.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListScope

/**
 * Call within a Lazy List scope such as a LazyColumn or LazyRow. This abstracts away the
 * general logic used in combination with GenericLazyItem objects.
 *
 * This function will invoke the BuildHeaderItems functions where it should and the BuildItem functions
 * for each item in the list.
 *
 * @param items - The GenericLazyItems that you would like to be rendered with the global rendering rules
 * @param processIntent a function you wish to carry back item and item header interactions with.
 */
@OptIn(ExperimentalFoundationApi::class)
fun <Intent : Any> LazyListScope.genericList(
    items: List<GenericLazyItem<Intent>>,
    processIntent: (Intent) -> Unit
) {
    items.forEachIndexed { index, genericLazyItem ->

        /*
        Draw Header if the section is different than the previous one.

        Note, because we are always drawing the BuildHeaderItem function even if the sectionMatcher is null
        and the BuildHeaderItem function is empty, if a header is showing and then an empty
        BuildHeaderItem function is called, the existing header will get pushed out when the
        item with the empty header is scrolled to the top.

        If you would like to only handle headers that exist then check for null on the
        sectionMatcher before entering this if statement.
         */
        if (index == 0 || genericLazyItem.sectionMatcher() != items[index - 1].sectionMatcher()) {
            stickyHeader {
                genericLazyItem.BuildHeaderItem(processIntent = processIntent)
            }
        }

        /*
        Calls the BuildItem function and provides the process Intent function.

        This uses the key from the GenericLazyItem to ensure re-composition doesn't occur just
        because an item is moved in the list by position.

        This uses the ContentType block to help re-use compositions of the same type.
         */
        item(
            key = genericLazyItem.itemKey(),
            contentType = genericLazyItem::class
        ) {
            genericLazyItem.BuildItem(processIntent = processIntent)
        }
    }
}