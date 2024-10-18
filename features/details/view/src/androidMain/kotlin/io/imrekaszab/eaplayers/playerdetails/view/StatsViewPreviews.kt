package io.imrekaszab.eaplayers.playerdetails.view

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.imrekaszab.eaplayers.domain.model.StatEntry
import io.imrekaszab.eaplayers.theme.preview.PreviewBox
import io.imrekaszab.eaplayers.view.StatsView

@Preview(name = "StatsViewPreviewLight", group = "Components")
@Preview(
    name = "StatsViewPreviewDark",
    group = "Components",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun StatsViewPreview() {
    val statList = listOf(
        StatEntry(displayName = "PAC", value = 90, diff = 0, isAnimated = true),
        StatEntry(displayName = "SHO", value = 45, diff = 0, isAnimated = true),
        StatEntry(displayName = "PHY", value = 24, diff = 0, isAnimated = true),
        StatEntry(displayName = "Strength", value = 100, diff = 0, isAnimated = true),
        StatEntry(displayName = "Agility", value = 50, diff = 0, isAnimated = true),
        StatEntry(displayName = "Intelligence", value = 25, diff = 0, isAnimated = true)
    )
    PreviewBox {
        StatsView(stats = statList, isMainStatsExpanded = true, isOtherStatsExpanded = true)
    }
}
