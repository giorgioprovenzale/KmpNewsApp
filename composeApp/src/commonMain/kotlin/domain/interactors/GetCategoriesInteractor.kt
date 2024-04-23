package domain.interactors

import domain.models.Category

class GetCategoriesInteractor() {
    suspend operator fun invoke(): List<Category> {
        val categories = mutableSetOf<Category>()
        categories.add(Category.Business)
        categories.add(Category.Entertainment)
        categories.add(Category.General)
        categories.add(Category.Health)
        categories.add(Category.Science)
        categories.add(Category.Sports)
        categories.add(Category.Technology)
        return categories.toList()
    }
}