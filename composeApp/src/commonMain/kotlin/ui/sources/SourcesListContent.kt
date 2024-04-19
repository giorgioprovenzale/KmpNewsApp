package ui.sources

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
import ui.shared.TopAppBarDefaults

@ExperimentalMaterial3Api
@Composable
fun SourcesListContent(
    component: SourcesListComponent
) {
    val state = component.state.subscribeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.colors(),
                title = { Text(text = "Sources") },
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            SourcesList(
                sources = state.value.sources,
                onItemClicked = { component.onItemClicked(it) })
        }
    }
}
