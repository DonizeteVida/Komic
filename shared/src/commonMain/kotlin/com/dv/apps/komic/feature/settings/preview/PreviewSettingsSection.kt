package com.dv.apps.komic.feature.settings.preview

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dv.apps.komic.KomicTheme
import com.dv.apps.komic.domain.model.PreviewSettings
import com.dv.apps.komic.feature.settings.SettingsSection
import komic.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class State(
    val previewSettings: PreviewSettings = PreviewSettings(
        verticalCount = 4,
        horizontalCount = 4,
        quality = PreviewSettings.Quality.HD
    )
)

internal sealed interface Intent {
    data class OnVerticalCountChanged(val size: Int) : Intent
    data class OnHorizontalCountChanged(val size: Int) : Intent
    data class OnQualityChanged(val quality: PreviewSettings.Quality) : Intent
}

@Composable
fun PreviewSettingsSection() {
    PreviewSettingsSection(State())
}

@Composable
internal fun PreviewSettingsSection(
    state: State,
    dispatchIntent: (Intent) -> Unit = {}
) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_title),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(
                onClick = { }
            ) {
                Icon(
                    painterResource(Res.drawable.ic_preview),
                    contentDescription = ""
                )
            }
        }

        HorizontalDivider(Modifier.padding(horizontal = 8.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_vertical_span)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnVerticalCountChanged(
                            state.previewSettings.verticalCount - 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = ""
                    )
                }
                Text("${state.previewSettings.verticalCount}")
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnVerticalCountChanged(
                            state.previewSettings.verticalCount + 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = ""
                    )
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_options_horizontal_span)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnHorizontalCountChanged(
                            state.previewSettings.horizontalCount - 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = ""
                    )
                }
                Text("${state.previewSettings.horizontalCount}")
                IconButton(onClick = {
                    dispatchIntent(
                        Intent.OnHorizontalCountChanged(
                            state.previewSettings.horizontalCount + 1
                        )
                    )
                }) {
                    Icon(
                        painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = ""
                    )
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_preview_quality)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                data class QualityView(
                    val quality: PreviewSettings.Quality,
                    val icon: DrawableResource,
                    val iconFilled: DrawableResource
                )
                listOf(
                    QualityView(
                        PreviewSettings.Quality.HD,
                        Res.drawable.ic_quality_hd,
                        Res.drawable.ic_quality_hd_filled
                    ),
                    QualityView(
                        PreviewSettings.Quality.FULL_HD,
                        Res.drawable.ic_quality_full_hd,
                        Res.drawable.ic_quality_full_hd_filled
                    ),
                    QualityView(
                        PreviewSettings.Quality.TWO_K,
                        Res.drawable.ic_quality_2k,
                        Res.drawable.ic_quality_2k_filled
                    ),
                    QualityView(
                        PreviewSettings.Quality.FOUR_K,
                        Res.drawable.ic_quality_4k,
                        Res.drawable.ic_quality_4k_filled
                    )
                ).forEach {
                    IconButton(onClick = {
                        if (it.quality == state.previewSettings.quality) return@IconButton
                        dispatchIntent(Intent.OnQualityChanged(it.quality))
                    }) {
                        Icon(
                            painterResource(
                                if (it.quality == state.previewSettings.quality) {
                                    it.iconFilled
                                } else {
                                    it.icon
                                }
                            ),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsSectionPreview() {
    KomicTheme {
        SettingsSection {
            PreviewSettingsSection()
        }
    }
}

