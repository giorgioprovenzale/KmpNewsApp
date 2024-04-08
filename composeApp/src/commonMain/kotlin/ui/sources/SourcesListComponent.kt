package ui.sources

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Source
import domain.repositories.SourcesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SourcesListComponent(
    private val componentContext: ComponentContext,
    private val onSourceClicked: (Source) -> Unit,
) : KoinComponent, ComponentContext by componentContext {

    private val sourcesRepository: SourcesRepository by inject()

    private val _state = MutableValue(SourcesState(sources = emptyList()))
    val state: Value<SourcesState> = _state

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val sources = sourcesRepository.getSources()
            _state.value = SourcesState(sources = sources)
        }
    }

    fun onItemClicked(item: Source) {
        onSourceClicked(item)
    }
}