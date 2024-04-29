# NewsApp
NewsApp is an open-source mobile application developed using [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/).  
NewsApp aims to provide a seamless news reading experience for both Android and iOS users. The app fetches news articles from various
online  
newspapers through the [NewsAPI.org](https://newsapi.org/) website.

## Setup

#### NewsApi API Key

To run the application you need to supply an API key from  [NewsAPI.org](https://newsapi.org/). When you get the key please  
add `secrets.properties` file inside `composeApp` folder.  
Then put the API Key in the `secrets.properties`:

    API_KEY=<YOUR_API_KEY> 

## Dependencies

The project is based on [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/) and the following dependencies:

- **[Decompose](https://arkivanov.github.io/Decompose/)  :** Lifecycle-aware business logic components, with routing functionality
- **[Koin](https://insert-koin.io/docs/setup/koin)  :** Dependency injector
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  :** Asynchronous programming
- **[Ktor](https://ktor.io/docs/welcome.html)  :** Type safe HTTP client
- **[Kotlinx](https://github.com/Kotlin/kotlinx.serialization)  :** JSON serializer/deserializer
- **[Material3](https://m3.material.io/)  :** UI - Light and Dark

## Video

#### Android
 
https://github.com/giorgioprovenzale/KmpNewsApp/assets/3489832/2b86d7ad-5ee5-4730-bad1-24711d0e1695
  
#### IOS


https://github.com/giorgioprovenzale/KmpNewsApp/assets/3489832/474dbc43-5ba4-442b-a487-28d8b3af7c74

## Contributing
Feel free to fork and modify the code as you prefer on your side. PR are welcome here
  
