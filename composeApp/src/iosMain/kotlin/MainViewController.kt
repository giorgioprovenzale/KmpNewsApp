import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.defaultImageResultMemoryCache
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import okio.Path.Companion.toPath
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import root.RootComponent
import root.RootContent

@ExperimentalDecomposeApi
@ExperimentalMaterial3Api
@ExperimentalResourceApi
fun MainViewController() = ComposeUIViewController {
    Napier.base(DebugAntilog())

    val rootComponent = remember {
        RootComponent(
            componentContext = DefaultComponentContext(LifecycleRegistry()),
        )
    }

    CompositionLocalProvider(
        LocalImageLoader provides remember { generateImageLoader() },
    ) {
        RootContent(rootComponent)
    }
}

fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 100 success image result, without bitmap
            defaultImageResultMemoryCache()
            bitmapMemoryCacheConfig {
                maxSize(32 * 1024 * 1024)
            }
            diskCacheConfig {
                directory(getCacheDir().toPath().resolve("image_cache"))
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}

private fun getCacheDir(): String {
    return NSSearchPathForDirectoriesInDomains(
        NSCachesDirectory,
        NSUserDomainMask,
        true,
    ).first() as String
}