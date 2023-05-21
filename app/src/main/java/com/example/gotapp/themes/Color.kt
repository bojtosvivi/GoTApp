package com.example.gotapp.themes

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

val got_grey_light = Color(0xFFA29F9F)
val got_grey_dark = Color(0xFF716f6f)
val got_gold_light = Color(0xFFb78f4c)
val got_gold_dark = Color(0xFFaa5F00)
val got_yellow = Color(0xFFFFD500)
val got_red = Color(0xFF86090A)
val got_black = Color(0xFF333333)
val got_white = Color(0xFFFFFFFF)

val got_textColor: Color
@Composable
@ReadOnlyComposable
get() = if (isSystemInDarkTheme()) got_white else got_black

val gotLightColors = lightColors(
    primary = got_gold_light,
    primaryVariant = got_gold_light,
    secondary = got_grey_dark,
    secondaryVariant = got_grey_dark,
    background = got_white,
    surface = got_white,
    error = got_yellow,
    onPrimary = got_gold_light,
    onSecondary = got_gold_light,
    onBackground = got_grey_light,
    onSurface = got_white,
    onError = got_yellow
)

val gotDarkColors = darkColors(
    primary = got_gold_dark,
    primaryVariant = got_gold_dark,
    secondary = got_grey_light,
    secondaryVariant = got_grey_light,
    background = got_black,
    surface = got_black,
    error = got_yellow,
    onPrimary = got_gold_dark,
    onSecondary = got_gold_dark,
    onBackground = got_grey_dark,
    onSurface = got_black,
    onError = got_yellow
)