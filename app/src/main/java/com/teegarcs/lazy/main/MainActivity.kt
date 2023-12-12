package com.teegarcs.lazy.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.teegarcs.lazy.core.observeSideEffects
import com.teegarcs.lazy.ui.theme.LazyTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val mainUI = MainUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyTheme {
                val state by viewModel.viewState.collectAsState()
                mainUI.BuildUI(state = state, processIntent = viewModel::processIntent)
            }
        }

        viewModel.observeSideEffects(lifecycle) {
            when (it) {
                is MainSE.MainMessage -> Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
