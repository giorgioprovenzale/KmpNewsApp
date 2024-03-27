package domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("author")
    val author: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: String,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String?
)
