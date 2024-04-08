package domain.interactors

import source.ApiService
import source.data.SourceResponse

class GetSourcesInteractor(
    private val apiService: ApiService
) {

    suspend operator fun invoke(): List<SourceResponse> = apiService.getSources().sources.filterNot {
        it.id.isNullOrEmpty() || it.description.isNullOrEmpty()
    }
}