package source

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import source.data.NewsResponse
import source.data.SourcesResponse

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun getHeadlines(): NewsResponse = httpClient.get("top-headlines").body<NewsResponse>()

    suspend fun getSources(): SourcesResponse = httpClient.get("top-headlines/sources").body<SourcesResponse>()
}