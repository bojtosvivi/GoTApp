package com.example.gotapp.model

sealed class UIState {

    object Loaded: UIState()

    data class Failed(val message: String): UIState()

    object Loading: UIState()
}
