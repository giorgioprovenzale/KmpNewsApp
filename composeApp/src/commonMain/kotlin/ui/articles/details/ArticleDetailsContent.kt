package ui.articles.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.shared.ArticleDetails
import ui.shared.TopBar

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun ArticleDetailsContent(
    component: ArticleDetailsComponent,
    useDarkTheme: Boolean,
    onThemeChange: () -> Unit
) {

    val state = component.state.subscribeAsState()
    val article = state.value.item

    Scaffold(topBar = {
        TopBar(
            title = "",
            showBack = true,
            useDarkTheme = useDarkTheme,
            onThemeChangeClicked = { onThemeChange() },
            onBackClicked = { component.onBackClicked() },
        )
    }) { paddings ->
        ArticleDetails(
            article = state.value.item,
            modifier = Modifier.padding(paddings)
        )
    }
}