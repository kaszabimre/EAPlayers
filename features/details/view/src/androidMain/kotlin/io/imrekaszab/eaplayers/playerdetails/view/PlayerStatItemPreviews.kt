package io.imrekaszab.eaplayers.playerdetails.view

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.team
import io.imrekaszab.eaplayers.domain.model.MockPlayer
import io.imrekaszab.eaplayers.theme.preview.PreviewBox
import io.imrekaszab.eaplayers.view.PlayerStatItem
import org.jetbrains.compose.resources.stringResource

@Preview(name = "PlayerStatItemPreviewLight", group = "Components")
@Preview(
    name = "PlayerStatItemPreviewDark",
    group = "Components",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PlayerStatItemPreview() {
    PreviewBox {
        PlayerStatItem(
            imageUrl = MockPlayer.createMockPlayer().team.imageUrl,
            stat = stringResource(Res.string.team),
            label = MockPlayer.createMockPlayer().team.label
        )
    }
}
