package com.timedsilence.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.timedsilence.ui.theme.backgroundDark
import com.timedsilence.ui.theme.backgroundDarkHighContrast
import com.timedsilence.ui.theme.backgroundDarkMediumContrast
import com.timedsilence.ui.theme.backgroundLight
import com.timedsilence.ui.theme.backgroundLightHighContrast
import com.timedsilence.ui.theme.backgroundLightMediumContrast
import com.timedsilence.ui.theme.errorContainerDark
import com.timedsilence.ui.theme.errorContainerDarkHighContrast
import com.timedsilence.ui.theme.errorContainerDarkMediumContrast
import com.timedsilence.ui.theme.errorContainerLight
import com.timedsilence.ui.theme.errorContainerLightHighContrast
import com.timedsilence.ui.theme.errorContainerLightMediumContrast
import com.timedsilence.ui.theme.errorDark
import com.timedsilence.ui.theme.errorDarkHighContrast
import com.timedsilence.ui.theme.errorDarkMediumContrast
import com.timedsilence.ui.theme.errorLight
import com.timedsilence.ui.theme.errorLightHighContrast
import com.timedsilence.ui.theme.errorLightMediumContrast
import com.timedsilence.ui.theme.inverseOnSurfaceDark
import com.timedsilence.ui.theme.inverseOnSurfaceDarkHighContrast
import com.timedsilence.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.timedsilence.ui.theme.inverseOnSurfaceLight
import com.timedsilence.ui.theme.inverseOnSurfaceLightHighContrast
import com.timedsilence.ui.theme.inverseOnSurfaceLightMediumContrast
import com.timedsilence.ui.theme.inversePrimaryDark
import com.timedsilence.ui.theme.inversePrimaryDarkHighContrast
import com.timedsilence.ui.theme.inversePrimaryDarkMediumContrast
import com.timedsilence.ui.theme.inversePrimaryLight
import com.timedsilence.ui.theme.inversePrimaryLightHighContrast
import com.timedsilence.ui.theme.inversePrimaryLightMediumContrast
import com.timedsilence.ui.theme.inverseSurfaceDark
import com.timedsilence.ui.theme.inverseSurfaceDarkHighContrast
import com.timedsilence.ui.theme.inverseSurfaceDarkMediumContrast
import com.timedsilence.ui.theme.inverseSurfaceLight
import com.timedsilence.ui.theme.inverseSurfaceLightHighContrast
import com.timedsilence.ui.theme.inverseSurfaceLightMediumContrast
import com.timedsilence.ui.theme.onBackgroundDark
import com.timedsilence.ui.theme.onBackgroundDarkHighContrast
import com.timedsilence.ui.theme.onBackgroundDarkMediumContrast
import com.timedsilence.ui.theme.onBackgroundLight
import com.timedsilence.ui.theme.onBackgroundLightHighContrast
import com.timedsilence.ui.theme.onBackgroundLightMediumContrast
import com.timedsilence.ui.theme.onErrorContainerDark
import com.timedsilence.ui.theme.onErrorContainerDarkHighContrast
import com.timedsilence.ui.theme.onErrorContainerDarkMediumContrast
import com.timedsilence.ui.theme.onErrorContainerLight
import com.timedsilence.ui.theme.onErrorContainerLightHighContrast
import com.timedsilence.ui.theme.onErrorContainerLightMediumContrast
import com.timedsilence.ui.theme.onErrorDark
import com.timedsilence.ui.theme.onErrorDarkHighContrast
import com.timedsilence.ui.theme.onErrorDarkMediumContrast
import com.timedsilence.ui.theme.onErrorLight
import com.timedsilence.ui.theme.onErrorLightHighContrast
import com.timedsilence.ui.theme.onErrorLightMediumContrast
import com.timedsilence.ui.theme.onPrimaryContainerDark
import com.timedsilence.ui.theme.onPrimaryContainerDarkHighContrast
import com.timedsilence.ui.theme.onPrimaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.onPrimaryContainerLight
import com.timedsilence.ui.theme.onPrimaryContainerLightHighContrast
import com.timedsilence.ui.theme.onPrimaryContainerLightMediumContrast
import com.timedsilence.ui.theme.onPrimaryDark
import com.timedsilence.ui.theme.onPrimaryDarkHighContrast
import com.timedsilence.ui.theme.onPrimaryDarkMediumContrast
import com.timedsilence.ui.theme.onPrimaryLight
import com.timedsilence.ui.theme.onPrimaryLightHighContrast
import com.timedsilence.ui.theme.onPrimaryLightMediumContrast
import com.timedsilence.ui.theme.onSecondaryContainerDark
import com.timedsilence.ui.theme.onSecondaryContainerDarkHighContrast
import com.timedsilence.ui.theme.onSecondaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.onSecondaryContainerLight
import com.timedsilence.ui.theme.onSecondaryContainerLightHighContrast
import com.timedsilence.ui.theme.onSecondaryContainerLightMediumContrast
import com.timedsilence.ui.theme.onSecondaryDark
import com.timedsilence.ui.theme.onSecondaryDarkHighContrast
import com.timedsilence.ui.theme.onSecondaryDarkMediumContrast
import com.timedsilence.ui.theme.onSecondaryLight
import com.timedsilence.ui.theme.onSecondaryLightHighContrast
import com.timedsilence.ui.theme.onSecondaryLightMediumContrast
import com.timedsilence.ui.theme.onSurfaceDark
import com.timedsilence.ui.theme.onSurfaceDarkHighContrast
import com.timedsilence.ui.theme.onSurfaceDarkMediumContrast
import com.timedsilence.ui.theme.onSurfaceLight
import com.timedsilence.ui.theme.onSurfaceLightHighContrast
import com.timedsilence.ui.theme.onSurfaceLightMediumContrast
import com.timedsilence.ui.theme.onSurfaceVariantDark
import com.timedsilence.ui.theme.onSurfaceVariantDarkHighContrast
import com.timedsilence.ui.theme.onSurfaceVariantDarkMediumContrast
import com.timedsilence.ui.theme.onSurfaceVariantLight
import com.timedsilence.ui.theme.onSurfaceVariantLightHighContrast
import com.timedsilence.ui.theme.onSurfaceVariantLightMediumContrast
import com.timedsilence.ui.theme.onTertiaryContainerDark
import com.timedsilence.ui.theme.onTertiaryContainerDarkHighContrast
import com.timedsilence.ui.theme.onTertiaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.onTertiaryContainerLight
import com.timedsilence.ui.theme.onTertiaryContainerLightHighContrast
import com.timedsilence.ui.theme.onTertiaryContainerLightMediumContrast
import com.timedsilence.ui.theme.onTertiaryDark
import com.timedsilence.ui.theme.onTertiaryDarkHighContrast
import com.timedsilence.ui.theme.onTertiaryDarkMediumContrast
import com.timedsilence.ui.theme.onTertiaryLight
import com.timedsilence.ui.theme.onTertiaryLightHighContrast
import com.timedsilence.ui.theme.onTertiaryLightMediumContrast
import com.timedsilence.ui.theme.outlineDark
import com.timedsilence.ui.theme.outlineDarkHighContrast
import com.timedsilence.ui.theme.outlineDarkMediumContrast
import com.timedsilence.ui.theme.outlineLight
import com.timedsilence.ui.theme.outlineLightHighContrast
import com.timedsilence.ui.theme.outlineLightMediumContrast
import com.timedsilence.ui.theme.outlineVariantDark
import com.timedsilence.ui.theme.outlineVariantDarkHighContrast
import com.timedsilence.ui.theme.outlineVariantDarkMediumContrast
import com.timedsilence.ui.theme.outlineVariantLight
import com.timedsilence.ui.theme.outlineVariantLightHighContrast
import com.timedsilence.ui.theme.outlineVariantLightMediumContrast
import com.timedsilence.ui.theme.primaryContainerDark
import com.timedsilence.ui.theme.primaryContainerDarkHighContrast
import com.timedsilence.ui.theme.primaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.primaryContainerLight
import com.timedsilence.ui.theme.primaryContainerLightHighContrast
import com.timedsilence.ui.theme.primaryContainerLightMediumContrast
import com.timedsilence.ui.theme.primaryDark
import com.timedsilence.ui.theme.primaryDarkHighContrast
import com.timedsilence.ui.theme.primaryDarkMediumContrast
import com.timedsilence.ui.theme.primaryLight
import com.timedsilence.ui.theme.primaryLightHighContrast
import com.timedsilence.ui.theme.primaryLightMediumContrast
import com.timedsilence.ui.theme.scrimDark
import com.timedsilence.ui.theme.scrimDarkHighContrast
import com.timedsilence.ui.theme.scrimDarkMediumContrast
import com.timedsilence.ui.theme.scrimLight
import com.timedsilence.ui.theme.scrimLightHighContrast
import com.timedsilence.ui.theme.scrimLightMediumContrast
import com.timedsilence.ui.theme.secondaryContainerDark
import com.timedsilence.ui.theme.secondaryContainerDarkHighContrast
import com.timedsilence.ui.theme.secondaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.secondaryContainerLight
import com.timedsilence.ui.theme.secondaryContainerLightHighContrast
import com.timedsilence.ui.theme.secondaryContainerLightMediumContrast
import com.timedsilence.ui.theme.secondaryDark
import com.timedsilence.ui.theme.secondaryDarkHighContrast
import com.timedsilence.ui.theme.secondaryDarkMediumContrast
import com.timedsilence.ui.theme.secondaryLight
import com.timedsilence.ui.theme.secondaryLightHighContrast
import com.timedsilence.ui.theme.secondaryLightMediumContrast
import com.timedsilence.ui.theme.surfaceBrightDark
import com.timedsilence.ui.theme.surfaceBrightDarkHighContrast
import com.timedsilence.ui.theme.surfaceBrightDarkMediumContrast
import com.timedsilence.ui.theme.surfaceBrightLight
import com.timedsilence.ui.theme.surfaceBrightLightHighContrast
import com.timedsilence.ui.theme.surfaceBrightLightMediumContrast
import com.timedsilence.ui.theme.surfaceContainerDark
import com.timedsilence.ui.theme.surfaceContainerDarkHighContrast
import com.timedsilence.ui.theme.surfaceContainerDarkMediumContrast
import com.timedsilence.ui.theme.surfaceContainerHighDark
import com.timedsilence.ui.theme.surfaceContainerHighDarkHighContrast
import com.timedsilence.ui.theme.surfaceContainerHighDarkMediumContrast
import com.timedsilence.ui.theme.surfaceContainerHighLight
import com.timedsilence.ui.theme.surfaceContainerHighLightHighContrast
import com.timedsilence.ui.theme.surfaceContainerHighLightMediumContrast
import com.timedsilence.ui.theme.surfaceContainerHighestDark
import com.timedsilence.ui.theme.surfaceContainerHighestDarkHighContrast
import com.timedsilence.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.timedsilence.ui.theme.surfaceContainerHighestLight
import com.timedsilence.ui.theme.surfaceContainerHighestLightHighContrast
import com.timedsilence.ui.theme.surfaceContainerHighestLightMediumContrast
import com.timedsilence.ui.theme.surfaceContainerLight
import com.timedsilence.ui.theme.surfaceContainerLightHighContrast
import com.timedsilence.ui.theme.surfaceContainerLightMediumContrast
import com.timedsilence.ui.theme.surfaceContainerLowDark
import com.timedsilence.ui.theme.surfaceContainerLowDarkHighContrast
import com.timedsilence.ui.theme.surfaceContainerLowDarkMediumContrast
import com.timedsilence.ui.theme.surfaceContainerLowLight
import com.timedsilence.ui.theme.surfaceContainerLowLightHighContrast
import com.timedsilence.ui.theme.surfaceContainerLowLightMediumContrast
import com.timedsilence.ui.theme.surfaceContainerLowestDark
import com.timedsilence.ui.theme.surfaceContainerLowestDarkHighContrast
import com.timedsilence.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.timedsilence.ui.theme.surfaceContainerLowestLight
import com.timedsilence.ui.theme.surfaceContainerLowestLightHighContrast
import com.timedsilence.ui.theme.surfaceContainerLowestLightMediumContrast
import com.timedsilence.ui.theme.surfaceDark
import com.timedsilence.ui.theme.surfaceDarkHighContrast
import com.timedsilence.ui.theme.surfaceDarkMediumContrast
import com.timedsilence.ui.theme.surfaceDimDark
import com.timedsilence.ui.theme.surfaceDimDarkHighContrast
import com.timedsilence.ui.theme.surfaceDimDarkMediumContrast
import com.timedsilence.ui.theme.surfaceDimLight
import com.timedsilence.ui.theme.surfaceDimLightHighContrast
import com.timedsilence.ui.theme.surfaceDimLightMediumContrast
import com.timedsilence.ui.theme.surfaceLight
import com.timedsilence.ui.theme.surfaceLightHighContrast
import com.timedsilence.ui.theme.surfaceLightMediumContrast
import com.timedsilence.ui.theme.surfaceVariantDark
import com.timedsilence.ui.theme.surfaceVariantDarkHighContrast
import com.timedsilence.ui.theme.surfaceVariantDarkMediumContrast
import com.timedsilence.ui.theme.surfaceVariantLight
import com.timedsilence.ui.theme.surfaceVariantLightHighContrast
import com.timedsilence.ui.theme.surfaceVariantLightMediumContrast
import com.timedsilence.ui.theme.tertiaryContainerDark
import com.timedsilence.ui.theme.tertiaryContainerDarkHighContrast
import com.timedsilence.ui.theme.tertiaryContainerDarkMediumContrast
import com.timedsilence.ui.theme.tertiaryContainerLight
import com.timedsilence.ui.theme.tertiaryContainerLightHighContrast
import com.timedsilence.ui.theme.tertiaryContainerLightMediumContrast
import com.timedsilence.ui.theme.tertiaryDark
import com.timedsilence.ui.theme.tertiaryDarkHighContrast
import com.timedsilence.ui.theme.tertiaryDarkMediumContrast
import com.timedsilence.ui.theme.tertiaryLight
import com.timedsilence.ui.theme.tertiaryLightHighContrast
import com.timedsilence.ui.theme.tertiaryLightMediumContrast
import com.timedsilence.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = com.timedsilence.ui.theme.ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> com.timedsilence.ui.theme.darkScheme
      else -> com.timedsilence.ui.theme.lightScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

