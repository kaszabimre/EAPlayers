package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.main_stats
import eaplayers.features.details.view.generated.resources.other_stats
import io.imrekaszab.eaplayers.domain.model.StatEntry
import io.imrekaszab.eaplayers.theme.AppTheme
import io.imrekaszab.eaplayers.theme.widgets.AnimatedCircularProgressIndicator
import io.imrekaszab.eaplayers.theme.widgets.AnimatedLinearProgressIndicator
import org.jetbrains.compose.resources.stringResource

private const val MAIN_STAT_TEXT_SIZE = 3

@Composable
fun StatsView(
    stats: List<StatEntry>,
    isMainStatsExpanded: Boolean,
    isOtherStatsExpanded: Boolean,
    onMainStatsExpandClick: () -> Unit = {},
    onOtherStatsExpandClick: () -> Unit = {},
) {
    val mainStats = stats.filter { it.displayName.length == MAIN_STAT_TEXT_SIZE }
    val otherStats = stats.filter { it.displayName.length != MAIN_STAT_TEXT_SIZE }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Main Stats Section
        if (mainStats.isNotEmpty()) {
            ExpandableSection(
                title = stringResource(Res.string.main_stats),
                isExpanded = isMainStatsExpanded,
                onExpand = onMainStatsExpandClick
            ) {
                MainStatsGrid(mainStats)
            }
        }

        // Other Stats Section
        if (otherStats.isNotEmpty()) {
            Spacer(modifier = Modifier.height(AppTheme.dimens.margin.small))
            ExpandableSection(
                title = stringResource(Res.string.other_stats),
                isExpanded = isOtherStatsExpanded,
                onExpand = onOtherStatsExpandClick
            ) {
                otherStats.forEach { stat ->
                    StatItem(stat = stat)
                }
            }
        }
    }
}

@Composable
fun MainStatsGrid(stats: List<StatEntry>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        stats.chunked(MAIN_STAT_TEXT_SIZE).forEach { rowStats ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowStats.forEach { stat ->
                    MainStatItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(AppTheme.dimens.margin.tiny),
                        stat = stat
                    )
                }
            }
        }
    }
}

@Composable
fun MainStatItem(modifier: Modifier = Modifier, stat: StatEntry) {
    Column(
        modifier = modifier.padding(top = AppTheme.dimens.margin.smaller),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stat.displayName,
            style = AppTheme.typography.body.large.copy(fontWeight = FontWeight.Thin)
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.extraTiny))

        AnimatedCircularProgressIndicator(value = stat.value, isAnimated = stat.isAnimated)
        Text(
            text = "${stat.value}",
            style = AppTheme.typography.body.large.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(top = AppTheme.dimens.margin.tiny)
        )
    }
}

@Composable
fun StatItem(stat: StatEntry) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimens.margin.tiny)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stat.displayName,
                style = AppTheme.typography.body.large.copy(fontWeight = FontWeight.Thin)
            )
            Text(
                text = "${stat.value}",
                style = AppTheme.typography.body.large.copy(fontWeight = FontWeight.Medium),
            )
        }
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.extraTiny))
        AnimatedLinearProgressIndicator(value = stat.value, isAnimated = stat.isAnimated)
    }
}
