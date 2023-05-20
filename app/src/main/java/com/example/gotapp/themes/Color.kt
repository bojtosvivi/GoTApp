package com.example.gotapp.themes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class gotc {
    val grey = Color(0xFFA29F9F)
    val gold = Color(0xFF995F00)
    val red = Color(0xFF86090A)
    val black = Color(0xFF000000)
}

class CustomColors(
    primary: Color,
    text: Color,
    background: Color,
    success: Color,
    error: Color,
    isLight: Boolean,
) {
    var primary by mutableStateOf(primary)
        private set

    var text by mutableStateOf(text)
        private set

    var success by mutableStateOf(success)
        private set

    var error by mutableStateOf(error)
        private set

    var background by mutableStateOf(background)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun copy(
        primary: Color = this.primary,
        text: Color = this.text,
        background: Color = this.background,
        success: Color = this.success,
        error: Color = this.error,
        isLight: Boolean = this.isLight,
    ) = CustomColors(
        primary = primary,
        text = text,
        background = background,
        success = success,
        error = error,
        isLight = isLight,
    )
    // Will be explain later
    fun updateColorsFrom(other: CustomColors) {
        primary = other.primary
        text = other.text
        success = other.success
        background = other.background
        error = other.error
        isLight = other.isLight
    }
}
