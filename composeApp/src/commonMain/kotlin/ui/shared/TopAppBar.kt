package ui.shared

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import extensions.capitalized
import getPlatform
import isAndroid
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_24
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_ios_24
import kmpnewsapp.composeapp.generated.resources.ic_dark_mode_24
import kmpnewsapp.composeapp.generated.resources.ic_light_mode_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun TopBar(
    title: String,
    showBack: Boolean,
    useDarkTheme: Boolean,
    onThemeChangeClicked: () -> Unit,
    onBackClicked: (() -> Unit)? = null
) {
    TopAppBar(
        title = { TopBarTitle(title) },
        navigationIcon = { if (showBack) TopBarBackIcon(onBackClicked) },
        actions = { TopBarThemeIcon(useDarkTheme, onThemeChangeClicked) },
        colors = TopBarColors()
    )
}

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun CenteredTopBar(
    title: String,
    showBack: Boolean,
    useDarkTheme: Boolean,
    onThemeChangeClicked: () -> Unit,
    onBackClicked: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = { TopBarTitle(title) },
        navigationIcon = { if (showBack) TopBarBackIcon(onBackClicked) },
        actions = { TopBarThemeIcon(useDarkTheme, onThemeChangeClicked) },
        colors = TopBarColors()
    )
}

@Composable
private fun TopBarTitle(
    title: String
) = Text(
    text = title.capitalized(),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
)

@ExperimentalMaterial3Api
@Composable
private fun TopBarColors() = TopAppBarDefaults.topAppBarColors().copy(
    containerColor = MaterialTheme.colorScheme.primary,
    titleContentColor = MaterialTheme.colorScheme.onPrimary,
)

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
private fun TopBarBackIcon(
    onBackClicked: (() -> Unit)? = null
) = IconButton(onClick = { if (onBackClicked != null) onBackClicked() }) {
    Icon(
        imageVector = vectorResource(
            if (getPlatform().isAndroid())
                Res.drawable.ic_arrow_back_24
            else
                Res.drawable.ic_arrow_back_ios_24
        ),
        contentDescription = "back",
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
private fun TopBarThemeIcon(
    useDarkTheme: Boolean,
    onThemeChangeClicked: () -> Unit
) = IconButton(onClick = { onThemeChangeClicked() }) {
    Icon(
        imageVector = vectorResource(
            if (useDarkTheme)
                Res.drawable.ic_light_mode_24
            else
                Res.drawable.ic_dark_mode_24
        ),
        contentDescription = "change theme",
        tint = MaterialTheme.colorScheme.onPrimary
    )
}