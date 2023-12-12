package com.teegarcs.lazy.main

sealed class MainSE {
    data class MainMessage(val message: String) : MainSE()
}
