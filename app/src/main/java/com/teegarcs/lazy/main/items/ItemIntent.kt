package com.teegarcs.lazy.main.items

sealed class ItemIntent {
    data class ItemHeader(val headerTitle: String) : ItemIntent()
    data class ItemClicked(val title: String) : ItemIntent()
}
