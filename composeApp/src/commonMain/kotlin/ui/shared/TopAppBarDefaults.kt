package ui.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object TopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun colors(
        titleContentColor: Color = MaterialTheme.colors.onPrimary,
        navigationIconColor: Color = MaterialTheme.colors.onPrimary,
        actionIconColor: Color = MaterialTheme.colors.onPrimary,
        containerColor: Color = MaterialTheme.colors.primary,
    ): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        titleContentColor = titleContentColor,
        navigationIconContentColor = navigationIconColor,
        actionIconContentColor = actionIconColor,
        containerColor = containerColor
    )
}