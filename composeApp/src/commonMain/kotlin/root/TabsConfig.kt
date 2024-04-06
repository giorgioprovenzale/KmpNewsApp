package root

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.categories
import kmpnewsapp.composeapp.generated.resources.home
import kmpnewsapp.composeapp.generated.resources.sources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Serializable
sealed class TabsConfig {

    @Serializable
    data object HeadlinesTabConfig : TabsConfig()

    @Serializable
    data object SourcesTabConfig : TabsConfig()

    @Serializable
    data object CategoriesTabConfig : TabsConfig()
}

enum class TabKey {
    Home, Sources, Categories
}

data class TabItem(
    val type: TabsConfig,
    val icon: ImageVector,
    val key: TabKey,
)

fun tabItems() = listOf(
    TabItem(
        type = TabsConfig.HeadlinesTabConfig,
        icon = newspaper(),
        key = TabKey.Home,
    ),
    TabItem(
        type = TabsConfig.SourcesTabConfig,
        icon = sources(),
        key = TabKey.Sources,
    ),
    TabItem(
        type = TabsConfig.CategoriesTabConfig,
        icon = category(),
        key = TabKey.Categories,
    )
)

@ExperimentalResourceApi
@Composable
fun GetTabTitleByKey(key: TabKey) = when (key) {
    TabKey.Home -> stringResource(Res.string.home)
    TabKey.Sources -> stringResource(Res.string.sources)
    TabKey.Categories -> stringResource(Res.string.categories)
}

fun newspaper(): ImageVector = ImageVector.Builder(
    name = "newspaper",
    defaultWidth = 40.0.dp,
    defaultHeight = 40.0.dp,
    viewportWidth = 40.0f,
    viewportHeight = 40.0f
).apply {
    path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1f,
        stroke = null,
        strokeAlpha = 1f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1f,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(6.333f, 34.542f)
        quadToRelative(-1.041f, 0f, -1.791f, -0.75f)
        reflectiveQuadTo(3.792f, 32f)
        verticalLineTo(7.375f)
        quadToRelative(0f, -0.5f, 0.27f, -0.604f)
        quadToRelative(0.271f, -0.104f, 0.646f, 0.229f)
        lineToRelative(1.417f, 1.458f)
        lineTo(8f, 6.542f)
        quadToRelative(0.208f, -0.167f, 0.417f, -0.271f)
        quadToRelative(0.208f, -0.104f, 0.458f, -0.104f)
        reflectiveQuadToRelative(0.479f, 0.083f)
        quadToRelative(0.229f, 0.083f, 0.396f, 0.292f)
        lineToRelative(1.917f, 1.916f)
        lineToRelative(1.916f, -1.916f)
        quadToRelative(0.167f, -0.209f, 0.396f, -0.292f)
        quadToRelative(0.229f, -0.083f, 0.479f, -0.083f)
        quadToRelative(0.25f, 0f, 0.459f, 0.104f)
        quadToRelative(0.208f, 0.104f, 0.416f, 0.271f)
        lineToRelative(1.875f, 1.916f)
        lineToRelative(1.917f, -1.916f)
        quadToRelative(0.208f, -0.209f, 0.417f, -0.292f)
        quadToRelative(0.208f, -0.083f, 0.458f, -0.083f)
        reflectiveQuadToRelative(0.458f, 0.083f)
        quadToRelative(0.209f, 0.083f, 0.417f, 0.292f)
        lineToRelative(1.917f, 1.916f)
        lineToRelative(1.875f, -1.916f)
        quadToRelative(0.208f, -0.167f, 0.416f, -0.271f)
        quadToRelative(0.209f, -0.104f, 0.459f, -0.104f)
        reflectiveQuadToRelative(0.479f, 0.083f)
        quadToRelative(0.229f, 0.083f, 0.396f, 0.292f)
        lineToRelative(1.916f, 1.916f)
        lineToRelative(1.917f, -1.916f)
        quadToRelative(0.167f, -0.209f, 0.396f, -0.292f)
        quadToRelative(0.229f, -0.083f, 0.479f, -0.083f)
        quadToRelative(0.25f, 0f, 0.458f, 0.104f)
        quadToRelative(0.209f, 0.104f, 0.417f, 0.271f)
        lineToRelative(1.875f, 1.916f)
        lineTo(35.292f, 7f)
        quadToRelative(0.375f, -0.333f, 0.646f, -0.229f)
        quadToRelative(0.27f, 0.104f, 0.27f, 0.604f)
        verticalLineTo(32f)
        quadToRelative(0f, 1.042f, -0.729f, 1.792f)
        reflectiveQuadToRelative(-1.771f, 0.75f)
        close()
        moveToRelative(0f, -2f)
        horizontalLineTo(19f)
        verticalLineToRelative(-11.75f)
        horizontalLineTo(5.792f)
        verticalLineTo(32f)
        quadToRelative(0f, 0.25f, 0.146f, 0.396f)
        quadToRelative(0.145f, 0.146f, 0.395f, 0.146f)
        close()
        moveToRelative(14.667f, 0f)
        horizontalLineToRelative(12.708f)
        quadToRelative(0.209f, 0f, 0.354f, -0.146f)
        quadToRelative(0.146f, -0.146f, 0.146f, -0.396f)
        verticalLineToRelative(-4.333f)
        horizontalLineTo(21f)
        close()
        moveToRelative(0f, -6.875f)
        horizontalLineToRelative(13.208f)
        verticalLineToRelative(-4.875f)
        horizontalLineTo(21f)
        close()
        moveTo(5.792f, 18.792f)
        horizontalLineToRelative(28.416f)
        verticalLineToRelative(-6.084f)
        horizontalLineTo(5.792f)
        close()
    }
}.build()

fun category(): ImageVector = ImageVector.Builder(
    name = "category",
    defaultWidth = 40.0.dp,
    defaultHeight = 40.0.dp,
    viewportWidth = 40.0f,
    viewportHeight = 40.0f
).apply {
    path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1f,
        stroke = null,
        strokeAlpha = 1f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1f,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(14.458f, 17.583f)
        quadToRelative(-0.75f, 0f, -1.104f, -0.645f)
        quadToRelative(-0.354f, -0.646f, 0.063f, -1.271f)
        lineToRelative(5.625f, -9.125f)
        quadToRelative(0.333f, -0.625f, 1.041f, -0.604f)
        quadToRelative(0.709f, 0.02f, 1.084f, 0.604f)
        lineToRelative(5.583f, 9.125f)
        quadToRelative(0.417f, 0.625f, 0.042f, 1.271f)
        quadToRelative(-0.375f, 0.645f, -1.084f, 0.645f)
        close()
        moveToRelative(14.834f, 18.334f)
        quadToRelative(-2.834f, 0f, -4.73f, -1.917f)
        quadToRelative(-1.895f, -1.917f, -1.895f, -4.75f)
        quadToRelative(0f, -2.792f, 1.916f, -4.708f)
        quadToRelative(1.917f, -1.917f, 4.709f, -1.917f)
        quadToRelative(2.791f, 0f, 4.708f, 1.917f)
        quadToRelative(1.917f, 1.916f, 1.917f, 4.708f)
        quadToRelative(0f, 2.833f, -1.917f, 4.75f)
        quadToRelative(-1.917f, 1.917f, -4.708f, 1.917f)
        close()
        moveToRelative(-22.209f, -0.959f)
        quadToRelative(-0.541f, 0f, -0.875f, -0.354f)
        quadToRelative(-0.333f, -0.354f, -0.333f, -0.896f)
        verticalLineToRelative(-9f)
        quadToRelative(0f, -0.541f, 0.333f, -0.896f)
        quadToRelative(0.334f, -0.354f, 0.875f, -0.354f)
        horizontalLineToRelative(9.042f)
        quadToRelative(0.542f, 0f, 0.896f, 0.354f)
        quadToRelative(0.354f, 0.355f, 0.354f, 0.896f)
        verticalLineToRelative(9f)
        quadToRelative(0f, 0.584f, -0.354f, 0.917f)
        quadToRelative(-0.354f, 0.333f, -0.896f, 0.333f)
        close()
        moveToRelative(22.209f, -1.041f)
        quadToRelative(1.958f, 0f, 3.291f, -1.355f)
        quadToRelative(1.334f, -1.354f, 1.334f, -3.312f)
        quadToRelative(0f, -1.917f, -1.334f, -3.271f)
        quadToRelative(-1.333f, -1.354f, -3.291f, -1.354f)
        quadToRelative(-1.959f, 0f, -3.292f, 1.354f)
        quadToRelative(-1.333f, 1.354f, -1.333f, 3.313f)
        quadToRelative(0f, 1.916f, 1.333f, 3.27f)
        quadToRelative(1.333f, 1.355f, 3.292f, 1.355f)
        close()
        moveToRelative(-21.459f, -0.959f)
        horizontalLineToRelative(7.542f)
        verticalLineToRelative(-7.5f)
        horizontalLineTo(7.833f)
        close()
        moveToRelative(7.917f, -17.375f)
        horizontalLineToRelative(8.625f)
        lineToRelative(-4.292f, -6.916f)
        close()
        moveToRelative(4.333f, 0f)
        close()
        moveToRelative(-4.708f, 9.875f)
        close()
        moveToRelative(13.917f, 3.834f)
        close()
    }
}.build()

fun sources(): ImageVector = ImageVector.Builder(
    name = "library_books",
    defaultWidth = 40.0.dp,
    defaultHeight = 40.0.dp,
    viewportWidth = 40.0f,
    viewportHeight = 40.0f
).apply {
    path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1f,
        stroke = null,
        strokeAlpha = 1f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1f,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(16.375f, 18.167f)
        horizontalLineToRelative(12.042f)
        quadToRelative(0.458f, 0f, 0.729f, -0.292f)
        quadToRelative(0.271f, -0.292f, 0.271f, -0.708f)
        quadToRelative(0f, -0.417f, -0.271f, -0.709f)
        quadToRelative(-0.271f, -0.291f, -0.729f, -0.291f)
        horizontalLineTo(16.375f)
        quadToRelative(-0.417f, 0f, -0.708f, 0.291f)
        quadToRelative(-0.292f, 0.292f, -0.292f, 0.709f)
        quadToRelative(0f, 0.458f, 0.292f, 0.729f)
        quadToRelative(0.291f, 0.271f, 0.708f, 0.271f)
        close()
        moveToRelative(0f, 4.25f)
        horizontalLineToRelative(5.792f)
        quadToRelative(0.375f, 0f, 0.666f, -0.292f)
        quadToRelative(0.292f, -0.292f, 0.292f, -0.708f)
        quadToRelative(0f, -0.417f, -0.292f, -0.709f)
        quadToRelative(-0.291f, -0.291f, -0.666f, -0.291f)
        horizontalLineToRelative(-5.792f)
        quadToRelative(-0.417f, 0f, -0.708f, 0.291f)
        quadToRelative(-0.292f, 0.292f, -0.292f, 0.709f)
        quadToRelative(0f, 0.416f, 0.292f, 0.708f)
        quadToRelative(0.291f, 0.292f, 0.708f, 0.292f)
        close()
        moveToRelative(0f, -8.5f)
        horizontalLineToRelative(12.042f)
        quadToRelative(0.458f, 0f, 0.729f, -0.292f)
        quadToRelative(0.271f, -0.292f, 0.271f, -0.708f)
        quadToRelative(0f, -0.417f, -0.271f, -0.709f)
        quadToRelative(-0.271f, -0.291f, -0.729f, -0.291f)
        horizontalLineTo(16.375f)
        quadToRelative(-0.417f, 0f, -0.708f, 0.312f)
        quadToRelative(-0.292f, 0.313f, -0.292f, 0.688f)
        quadToRelative(0f, 0.416f, 0.292f, 0.708f)
        quadToRelative(0.291f, 0.292f, 0.708f, 0.292f)
        close()
        moveToRelative(-4.25f, 16.041f)
        quadToRelative(-1.042f, 0f, -1.771f, -0.729f)
        quadToRelative(-0.729f, -0.729f, -0.729f, -1.771f)
        verticalLineTo(6.875f)
        quadToRelative(0f, -1.042f, 0.729f, -1.771f)
        quadToRelative(0.729f, -0.729f, 1.771f, -0.729f)
        horizontalLineToRelative(20.583f)
        quadToRelative(1.042f, 0f, 1.771f, 0.729f)
        quadToRelative(0.729f, 0.729f, 0.729f, 1.771f)
        verticalLineToRelative(20.583f)
        quadToRelative(0f, 1.042f, -0.729f, 1.771f)
        quadToRelative(-0.729f, 0.729f, -1.771f, 0.729f)
        close()
        moveToRelative(0f, -1.958f)
        horizontalLineToRelative(20.583f)
        quadToRelative(0.209f, 0f, 0.354f, -0.146f)
        quadToRelative(0.146f, -0.146f, 0.146f, -0.396f)
        verticalLineTo(6.875f)
        quadToRelative(0f, -0.208f, -0.146f, -0.354f)
        quadToRelative(-0.145f, -0.146f, -0.354f, -0.146f)
        horizontalLineTo(12.125f)
        quadToRelative(-0.25f, 0f, -0.396f, 0.146f)
        quadToRelative(-0.146f, 0.146f, -0.146f, 0.354f)
        verticalLineToRelative(20.583f)
        quadToRelative(0f, 0.25f, 0.146f, 0.396f)
        quadToRelative(0.146f, 0.146f, 0.396f, 0.146f)
        close()
        moveToRelative(-4.833f, 6.792f)
        quadToRelative(-1.042f, 0f, -1.771f, -0.73f)
        quadToRelative(-0.729f, -0.729f, -0.729f, -1.77f)
        verticalLineTo(10.708f)
        quadToRelative(0f, -0.416f, 0.291f, -0.708f)
        quadToRelative(0.292f, -0.292f, 0.709f, -0.292f)
        quadToRelative(0.416f, 0f, 0.708f, 0.292f)
        reflectiveQuadToRelative(0.292f, 0.708f)
        verticalLineToRelative(21.584f)
        quadToRelative(0f, 0.208f, 0.146f, 0.354f)
        quadToRelative(0.145f, 0.146f, 0.354f, 0.146f)
        horizontalLineToRelative(21.583f)
        quadToRelative(0.417f, 0f, 0.708f, 0.291f)
        quadToRelative(0.292f, 0.292f, 0.292f, 0.709f)
        quadToRelative(0f, 0.416f, -0.292f, 0.708f)
        quadToRelative(-0.291f, 0.292f, -0.708f, 0.292f)
        close()
        moveToRelative(4.291f, -28.417f)
        verticalLineTo(28f)
        verticalLineTo(6.375f)
        close()
    }
}.build()

