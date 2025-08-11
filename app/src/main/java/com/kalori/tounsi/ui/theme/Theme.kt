package com.kalori.tounsi.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Seed = Color(0xFFEF6C00)

@Composable
fun KaloriTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Seed,
            secondary = Color(0xFF795548),
            tertiary = Color(0xFF4CAF50)
        ),
        typography = Typography(),
        content = content
    )
}
