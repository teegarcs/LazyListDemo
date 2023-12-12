package com.teegarcs.lazy.main

import com.teegarcs.lazy.core.GenericLazyItem
import com.teegarcs.lazy.main.items.ItemIntent

data class MainState(
    val lazyItems: List<GenericLazyItem<ItemIntent>> = emptyList()
)
