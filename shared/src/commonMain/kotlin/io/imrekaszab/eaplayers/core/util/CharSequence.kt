package io.imrekaszab.eaplayers.core.util

fun CharSequence.keepMatching(regex: Regex): CharSequence {
    val matchResult = regex.find(this)
    val values = matchResult?.groupValues
    return values?.joinToString().orEmpty()
}
