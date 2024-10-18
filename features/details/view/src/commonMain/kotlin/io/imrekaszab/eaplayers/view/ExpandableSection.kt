package io.imrekaszab.eaplayers.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.collapse_desc
import eaplayers.features.details.view.generated.resources.expand_desc
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

private const val ANIMATION_DURATION = 300

@Composable
fun ExpandableSection(
    title: String,
    isExpanded: Boolean,
    onExpand: () -> Unit,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(isExpanded) }

    // Rotation animation for the arrow
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = AppTheme.shapes.default.roundedTiny)
                .clickable {
                    expanded = !expanded
                    onExpand()
                }
                .padding(vertical = AppTheme.dimens.margin.tiny),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = AppTheme.typography.heading.medium,
                color = AppTheme.colorScheme.onBackground
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(if (expanded) Res.string.collapse_desc else Res.string.expand_desc),
                tint = AppTheme.colorScheme.onBackground,
                modifier = Modifier.rotate(rotation)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(ANIMATION_DURATION)) + shrinkVertically()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) { content() }
        }
    }
}
