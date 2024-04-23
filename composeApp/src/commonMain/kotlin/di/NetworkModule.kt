package di

import com.jpmobilelab.kmp.newsapp.BuildKonfig
import domain.interactors.GetCategoriesInteractor
import domain.interactors.GetHeadlinesArticlesByCategoryInteractor
import domain.interactors.GetHeadlinesArticlesBySourceInteractor
import domain.interactors.GetHeadlinesArticlesInteractor
import domain.interactors.GetSourcesInteractor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import source.ApiService

fun networkModule() = module {
    single {
        HttpClient {
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsapi.org"
                    path("v2/")
                    parameters.append("apiKey", BuildKonfig.API_KEY)
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

    single {
        ApiService(httpClient = get())
    }
    single {
        GetHeadlinesArticlesInteractor(apiService = get())
    }
    single {
        GetSourcesInteractor(apiService = get())
    }
    single {
        GetHeadlinesArticlesBySourceInteractor(apiService = get())
    }
    single {
        GetCategoriesInteractor()
    }
    single {
        GetHeadlinesArticlesByCategoryInteractor(apiService = get())
    }
}