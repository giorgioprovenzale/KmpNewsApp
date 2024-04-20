package extensions

fun String.capitalized() = replaceFirstChar(Char::titlecase)