package source.data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("articles")
    val articles: List<ArticleResponse>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)