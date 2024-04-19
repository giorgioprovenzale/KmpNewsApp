package ui.articles.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.shared.ArticlesList
import ui.shared.TopAppBarDefaults

@ExperimentalMaterial3Api
@Composable
fun ArticlesListContent(
    component: ArticlesListComponent
) {
    val state = component.state.subscribeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.colors(),
                title = { Text(text = state.value.topBarTitle.orEmpty()) },
                navigationIcon = {
                    if (state.value.showBackButton) {
                        IconButton(
                            onClick = { component.onBackClicked() }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            ArticlesList(
                articles = state.value.articles,
                onItemClicked = { component.onItemClicked(it) })
        }
    }
}
