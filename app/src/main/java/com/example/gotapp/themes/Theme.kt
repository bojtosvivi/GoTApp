package com.example.gotapp.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material.Typography
import androidx.compose.ui.unit.dp
import com.example.gotapp.R

@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) gotDarkColors else gotLightColors

    val shapes = MaterialTheme.shapes.copy(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(24.dp)
    )

    val typography = Typography(
        defaultFontFamily = FontFamily(Font(R.font.comme))
    )

    MaterialTheme(colors, shapes = shapes, typography = typography, content = content)
}