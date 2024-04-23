package ui.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Category
import domain.repositories.CategoriesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoriesListComponent(
    private val componentContext: ComponentContext,
    private val onCategorySelected: (Category) -> Unit,
) : KoinComponent, ComponentContext by componentContext {

    private val categoriesRepository: CategoriesRepository by inject()

    private val _state = MutableValue(CategoriesState(categories = emptyList()))
    val state: Value<CategoriesState> = _state

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val categories = categoriesRepository.getCategories()
            _state.value = CategoriesState(categories = categories)
        }
    }

    fun onItemClicked(item: Category) {
        onCategorySelected(item)
    }
}