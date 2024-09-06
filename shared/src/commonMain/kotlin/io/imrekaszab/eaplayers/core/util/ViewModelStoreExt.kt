@file:Suppress("PackageDirectoryMismatch", "InvalidPackageDeclaration")

package androidx.lifecycle

@Suppress("RestrictedApi")
fun ViewModelStore.getAll() = keys().map { get(it) }.toSet()
