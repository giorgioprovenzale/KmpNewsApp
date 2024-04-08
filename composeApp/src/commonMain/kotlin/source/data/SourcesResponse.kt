package source.data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("sources")
    val sources: List<SourceResponse>,
    @SerialName("status")
    val status: String,
)