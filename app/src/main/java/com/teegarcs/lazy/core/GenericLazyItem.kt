package com.teegarcs.lazy.core

import androidx.compose.runtime.Composable

/**
 * Base Class that represents a consistent pattern for how we will build LazyItems
 * and handle their interactions.
 */
abstract class GenericLazyItem<Intent : Any> {

    /**
     * The ItemKey is a unique key representation of your item. This defaults to
     * the classes hashcode but can be overridden if a specific key is needed.
     * This is required to be unique in the list of items and is used to ensure
     * that as content is removed/added individual items are not rebuilt just
     * due to positions changes.
     */
    open fun itemKey(): Int = hashCode()

    /**
     * This is used to determine whether to show a sticky header or not. Null is
     * valid and could match with other sections. If you wish to have a sticky header
     * simply implement one in the BuildHeaderItem function and then differentiate
     * your sections with the sectionMatcher.
     *
     * Note, that empty BuildHeaderItem functions called within a list of other headers
     * will push out drawn headers. This lets you have some items with section headers
     * and some without in the same list.
     */
    open fun sectionMatcher(): Int? = null

    /**
     * Called to build a header item based on whether the previous item's sectionMatcher
     * is the same as this one or not.
     *
     * Note, that empty BuildHeaderItem functions called within a list of other headers
     * will push out drawn headers. This lets you have some items with section headers
     * and some without in the same list.
     *
     * @param processIntent a function callback to enable header interactions to be processed
     */
    @Composable
    open fun BuildHeaderItem(
        processIntent: (Intent) -> Unit
    ) {
        //by default no header.
    }

    /**
     * Called to build the body of the GenericLazyItem.
     *
     * @param processIntent a function callback to enable item interactions to be processed
     */
    @Composable
    abstract fun BuildItem(
        processIntent: (Intent) -> Unit
    )
}
