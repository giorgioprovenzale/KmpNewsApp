package source.data

import domain.models.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String? = null
)

fun SourceResponse.toDomain() = Source(
    id = id.orEmpty(),
    name = name,
    description = description.orEmpty(),
)