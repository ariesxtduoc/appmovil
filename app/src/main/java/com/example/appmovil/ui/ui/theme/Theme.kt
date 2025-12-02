package com.example.appmovil.ui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define el esquema de colores para el modo claro (Light Theme)
private val LightColorScheme = lightColorScheme(
    primary = Primary,           // Verde Esmeralda
    onPrimary = OnPrimary,       // Blanco
    secondary = Secondary,       // Amarillo Mostaza
    background = Background,     // Blanco Suave
    surface = Surface,           // Blanco
    onBackground = OnBackground, // Gris Oscuro
    onSurface = OnSurface        // Gris Oscuro
    /* other default colors to override */
)

// Define el esquema de colores para el modo oscuro (Dark Theme)
// NOTA: Para HuertoHogar, podríamos definir un esquema oscuro simple o deshabilitar el modo oscuro.
/*
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    // ...
)
*/

@Composable
fun HuertoHogarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Controla si usa Dark Theme
    content: @Composable () -> Unit
) {
    // Usaremos el modo claro por defecto, ya que la propuesta de diseño no incluye tema oscuro
    val colorScheme = LightColorScheme

    // Si necesitas habilitar el modo oscuro más adelante, puedes usar la lógica:
    // val colorScheme = when {
    //     darkTheme -> DarkColorScheme
    //     else -> LightColorScheme
    // }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Aplica la tipografía definida en Type.kt
        content = content
    )
}