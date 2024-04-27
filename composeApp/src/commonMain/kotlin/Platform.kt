interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

fun Platform.isIos(): Boolean = name == "iOS"
fun Platform.isAndroid(): Boolean = name == "Android"