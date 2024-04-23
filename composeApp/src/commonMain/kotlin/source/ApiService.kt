package source

import domain.models.Category
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import source.data.NewsResponse
import source.data.SourcesResponse

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun getHeadlines(): NewsResponse = httpClient.get("top-headlines") {
        url {
            parameters.append("country", "us")
        }
    }.body<NewsResponse>()

    suspend fun getSources(): SourcesResponse = httpClient.get("top-headlines/sources") {
        url {
            parameters.append("country", "us")
        }
    }.body<SourcesResponse>()

    suspend fun getHeadlinesBySource(sourceId: String): NewsResponse = httpClient.get("top-headlines") {
        url {
            parameters.append("sources", sourceId)
        }
    }.body<NewsResponse>()

    suspend fun getHeadlinesByCategory(category: String): NewsResponse = httpClient.get("top-headlines") {
        url {
            parameters.append("category", category)
            parameters.append("country", "us")
        }
    }.body<NewsResponse>()

}