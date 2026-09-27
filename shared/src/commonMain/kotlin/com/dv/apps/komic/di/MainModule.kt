package com.dv.apps.komic.di

import com.dv.apps.komic.feature.settings.di.settingsModule
import com.dv.apps.komic.platformModule
import org.koin.dsl.module

val mainModule = module  {
    includes(
        platformModule,
        settingsModule,
    )
}