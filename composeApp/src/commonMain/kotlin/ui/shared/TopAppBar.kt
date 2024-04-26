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
import getPlatform
import isAndroid
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_24
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_ios_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun TopBar(
    title: String,
    showBack: Boolean,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { TopBarTitle(title) },
        navigationIcon = { if (showBack) TopBarBackIcon(onBackClicked) },
        colors = TopBarColors()
    )
}

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun CenteredTopBar(
    title: String,
    showBack: Boolean,
    onBackClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { TopBarTitle(title) },
        navigationIcon = { if (showBack) TopBarBackIcon(onBackClicked) },
        colors = TopBarColors()
    )
}

@Composable
private fun TopBarTitle(
    title: String
) = Text(
    text = title,
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
    onBackClicked: () -> Unit
) = IconButton(onClick = { onBackClicked() }) {
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