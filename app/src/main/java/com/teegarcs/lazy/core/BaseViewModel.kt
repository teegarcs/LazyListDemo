package com.teegarcs.lazy.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * BaseViewModel that sets up the structure for State, SideEffects, and Intents.
 */
abstract class BaseViewModel<VS : Any, SE : Any, I : Any> : ViewModel() {

    private val _viewState: MutableStateFlow<VS> by lazy {
        MutableStateFlow(buildInitialState())
    }
    val viewState: StateFlow<VS>
        get() = _viewState.asStateFlow()

    val currentState: VS
        get() = viewState.value

    private val _sideEffectsChannel = Channel<SE>(Channel.UNLIMITED)

    val sideEffects: Flow<SE>
        get() = _sideEffectsChannel.receiveAsFlow()

    /**
     * Abstract function required to be implemented to build the Initial UI State Object. This
     * State will automatically be supplied to the viewState.
     *
     * @return ViewState Object
     */
    abstract fun buildInitialState(): VS

    /**
     * Abstract function to be implemented to handle intents received from the UI.
     *
     * @param intent - Intent to be handled and reduced.
     */
    abstract fun processIntent(intent: I)

    /**
     * Function to post a SideEffect to the SideEffect Channel.
     *
     * @param sideEffect - the SideEffect to post to the channel.
     */
    protected fun sendSideEffect(sideEffect: SE) {
        _sideEffectsChannel.trySend(sideEffect)
    }

    /**
     * Update the MutableStateFlow with the provided State Object.
     *
     * @param updated - state to push to the MutableStateFlow
     * @return true if the StateFlow was updated successfully.
     */
    protected fun updateState(updated: VS): Boolean {
        _viewState.value = updated
        return if (_viewState.value != updated) {
            _viewState.value = updated
            true
        } else {
            false
        }
    }

    protected fun updateState(updateFunction: VS.() -> VS): Boolean =
        updateState(updateFunction(_viewState.value))
}

/**
 * Observe side effects emitted by the side effect channel.
 *
 * Side Effects will only be consumed when the lifecycle is in at least resumed state.
 */
inline fun <SE : Any> BaseViewModel<*, SE, *>.observeSideEffects(
    lifecycle: Lifecycle,
    crossinline sideEffectFunction: (SE) -> Unit
) {
    lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            sideEffects.collect {
                sideEffectFunction(it)
            }
        }
    }
}
