package domain.interactors

import source.ApiService
import source.data.ArticleResponse

class GetHeadlinesArticlesInteractor(
    private val apiService: ApiService
) {

    suspend operator fun invoke(): List<ArticleResponse> = apiService.getHeadlines().articles.filterNot {
        it.urlToImage.isNullOrEmpty()
    }
}