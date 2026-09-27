package com.dv.apps.komic.feature.settings.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dv.apps.komic.Folder
import com.dv.apps.komic.domain.repository.FolderRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FolderSourceSettingsSectionViewModel(
    private val folderRepository: FolderRepository
) : ViewModel() {
    private val isLoading = MutableStateFlow(0)

    internal val state = combine(
        isLoading.map { it > 0 },
        folderRepository.getFolders(),
        ::State
    ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State())

    internal fun handleIntent(intent: Intent) {
        when (intent) {
            Intent.OnFolderSyncRequested -> TODO()
            is Intent.OnFolderSelected -> onFolderSelected(intent.folder ?: return)
        }
    }

    private fun onFolderSelected(folder: Folder) {
        launch {
            folderRepository.addFolder(folder)
        }
    }

    private fun launch(block: suspend () -> Unit) {
        isLoading.update { it + 1 }
        viewModelScope.launch { block() }.invokeOnCompletion {
            isLoading.update { it - 1 }
        }
    }
}
