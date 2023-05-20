package com.example.gotapp.themes

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

 fun lightColors() = CustomColors(
    primary = Color(0xFFA29F9F),
    text = Color(0xFF86090A),
    background = Color(0xFF000000),
    success = Color(0xFF995F00),
    error = Color(0xFFD10F0F),
    isLight = true,
)

val LocalColors = staticCompositionLocalOf { lightColors() }

object CustomTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

@Composable
fun CustomTheme(
    colors: CustomColors = CustomTheme.colors,
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
    ) {
        ProvideTextStyle(typography.displayMedium, content = content)
    }
}