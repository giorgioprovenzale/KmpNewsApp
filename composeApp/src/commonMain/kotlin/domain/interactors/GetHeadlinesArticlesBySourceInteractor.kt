package domain.interactors

import source.ApiService
import source.data.ArticleResponse

class GetHeadlinesArticlesBySourceInteractor(
    private val apiService: ApiService
) {

    suspend operator fun invoke(sourceId: String): List<ArticleResponse> = apiService.getHeadlinesBySource(sourceId).articles.filterNot {
        it.urlToImage.isNullOrEmpty()
    }
}