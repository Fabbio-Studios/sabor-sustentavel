package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SustainableColorScheme = lightColorScheme(
  primary = EcoDeepGreen,
  onPrimary = EcoSurface,
  secondary = EcoLeafGreen,
  onSecondary = EcoSurface,
  tertiary = EcoLime,
  onTertiary = EcoInk,
  background = EcoParchment,
  onBackground = EcoInk,
  surface = EcoSurface,
  onSurface = EcoInk,
  error = EcoRustRed,
  onError = EcoSurface
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = SustainableColorScheme,
    typography = Typography,
    content = content
  )
}
