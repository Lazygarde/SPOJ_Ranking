package com.example.spojranking.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorScheme = darkColorScheme(
    primary = Color.White, // text 1
    secondary = Color.White, // text 2
    tertiary = Color(0xff1974e4), // button
    background = Color(0xff10111a),
    surface = Color(0xff393b3d),
    onSurface = Color(0xff4e5052), //spacer
    )

private val lightColorScheme = lightColorScheme(
    primary = Color.White, // text 1
    secondary = Color.Black, // text 2
    tertiary = Color.White, // button
    background = Color(0xff1a8cfd),
    surface = Color.White,
    onSurface = Color.Black

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SPOJRankingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val ourColorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = ourColorScheme,
        content = content
    )
}