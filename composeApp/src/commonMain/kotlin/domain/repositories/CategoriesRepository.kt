package domain.repositories

import domain.interactors.GetCategoriesInteractor
import domain.models.Category
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class CategoriesRepository(
    private val getCategoriesInteractor: GetCategoriesInteractor
) {

    suspend fun getCategories(): List<Category> = getCategoriesInteractor()
}