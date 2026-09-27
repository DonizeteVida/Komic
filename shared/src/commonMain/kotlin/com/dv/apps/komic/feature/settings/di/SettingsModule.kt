package com.dv.apps.komic.feature.settings.di

import com.dv.apps.komic.feature.settings.folder.FolderSourceSettingsSectionViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val settingsModule = module {
    viewModel<FolderSourceSettingsSectionViewModel>()
}