package domain.interactors

import domain.models.Category
import source.ApiService
import source.data.ArticleResponse

class GetHeadlinesArticlesByCategoryInteractor(
    private val apiService: ApiService
) {

    suspend operator fun invoke(category: Category): List<ArticleResponse> = apiService.getHeadlinesByCategory(category.name).articles.filterNot {
        it.urlToImage.isNullOrEmpty()
    }
}