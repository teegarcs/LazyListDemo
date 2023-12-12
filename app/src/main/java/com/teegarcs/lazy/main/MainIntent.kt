package com.teegarcs.lazy.main

import com.teegarcs.lazy.main.items.ItemIntent

sealed class MainIntent {
    data class ItemSelected(val intent: ItemIntent) : MainIntent()
}
