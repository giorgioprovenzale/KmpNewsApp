package source.data

import domain.models.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("author")
    val author: String,
    @SerialName("content")
    val content: String,
    @SerialName("description")
    val description: String,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: SourceResponse,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String
)

fun ArticleResponse.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.name,
    title = title,
    url = url,
    urlToImage = urlToImage
)