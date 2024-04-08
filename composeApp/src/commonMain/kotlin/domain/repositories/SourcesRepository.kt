package domain.repositories

import domain.interactors.GetSourcesInteractor
import domain.models.Source
import source.data.toDomain

class SourcesRepository(
    private val getSourcesInteractor: GetSourcesInteractor
) {

    suspend fun getSources(): List<Source> = getSourcesInteractor().map { it.toDomain() }
}