package io.imrekaszab.eaplayers.core.util

@Suppress("MagicNumber")
fun String.uppercaseIfThreeLetters() = if (this.length == 3) this.uppercase() else this

fun String.splitCamelCase() = this.replace(
    "(?<!^)([A-Z])".toRegex(),
    " $1"
).replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

fun String.displayNameToCamelCase() = this.split(" ").joinToString("") { word ->
    if (word == this.split(" ").first()) {
        word.lowercase()
    } else {
        word.replaceFirstChar { it.uppercase() }
    }
}
