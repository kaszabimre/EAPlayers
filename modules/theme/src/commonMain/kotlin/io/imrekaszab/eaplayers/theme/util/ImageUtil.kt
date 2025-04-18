package io.imrekaszab.eaplayers.theme.util

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.memory.MemoryCache
import coil3.request.crossfade

@Composable
fun EAImageLoader(): ImageLoader {
    val context = LocalPlatformContext.current
    return ImageLoader(context).newBuilder()
        .crossfade(true)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context = context, percent = 0.25)
                .build()
        }
        .build()
}
