package source

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import source.data.NewsResponse

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun getHeadlines(): NewsResponse = httpClient.get("/products").body<NewsResponse>()
}