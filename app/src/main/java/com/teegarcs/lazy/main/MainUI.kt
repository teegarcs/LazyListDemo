package com.teegarcs.lazy.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.teegarcs.lazy.core.BaseUI
import com.teegarcs.lazy.core.genericList

class MainUI : BaseUI<MainState, MainIntent>() {
    @Composable
    override fun BuildUI(state: MainState, processIntent: (MainIntent) -> Unit) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            genericList(state.lazyItems) { processIntent(MainIntent.ItemSelected(it)) }
        }
    }
}
