package com.dv.apps.komic.feature.settings.folder

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dv.apps.komic.Folder
import com.dv.apps.komic.FolderConstructor
import com.dv.apps.komic.KomicTheme
import com.dv.apps.komic.LocalFolderPicker
import com.dv.apps.komic.domain.model.FolderSettings
import com.dv.apps.komic.feature.settings.SettingsSection
import komic.shared.generated.resources.Res
import komic.shared.generated.resources.ic_folder_add
import komic.shared.generated.resources.settings_section_selected_folders_title
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class State(
    val isLoading: Boolean = false,
    val folderSettings: FolderSettings = FolderSettings()
)

internal sealed interface Intent {
    data class OnFileTreeSelected(val folder: Folder?) : Intent
}

@Composable
fun FolderSourceSettingsSection() {
    FolderSourceSettingsSection(
        State(
            folderSettings = FolderSettings(
            List(4) {
                FolderConstructor("/root/sdcard:folder/$it")
            }
        )
    ))
}

@Composable
internal fun FolderSourceSettingsSection(
    state: State,
    dispatchIntent: (Intent) -> Unit = {}
) {
    val co = rememberCoroutineScope()
    val folderPicker = LocalFolderPicker.current

    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_selected_folders_title),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(
                onClick = {
                    co.launch {
                        val folder = folderPicker()
                        dispatchIntent(Intent.OnFileTreeSelected(folder))
                    }
                }
            ) {
                Icon(
                    painterResource(Res.drawable.ic_folder_add),
                    contentDescription = ""
                )
            }
        }

        with(state.folderSettings) {
            if (folders.isNotEmpty()) {
                HorizontalDivider(
                    Modifier.padding(horizontal = 8.dp)
                )

                Column(Modifier.padding(vertical = 8.dp)) {
                    for (path in folders) {
                        Text(path.toString())
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FolderScreenPreview1() {
    KomicTheme {
        SettingsSection {
            FolderSourceSettingsSection(
                state = State(
                    folderSettings = FolderSettings(
                        emptyList()
                    )
                )
            )
        }
    }
}

@Preview
@Composable
private fun FolderScreenPreview2() {
    KomicTheme {
        SettingsSection {
            FolderSourceSettingsSection(
                state = State(
                    folderSettings = FolderSettings(
                        List(4) {
                            FolderConstructor("/root/sdcard:folder/$it")
                        }
                    )
                )
            )
        }
    }
}