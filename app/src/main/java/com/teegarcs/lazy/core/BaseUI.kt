package com.teegarcs.lazy.core

import androidx.compose.runtime.Composable

/**
 * Base composable class to enforce a consistent pattern and set up a path for other
 * extensible defaults to be added.
 */
abstract class BaseUI<State, Intent> {

    @Composable
    abstract fun BuildUI(state: State, processIntent: (Intent) -> Unit)
}
