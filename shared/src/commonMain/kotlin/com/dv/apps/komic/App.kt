package com.dv.apps.komic

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import komic.shared.generated.resources.*
import org.jetbrains.compose.resources.*

@Composable
fun App() {
    KomicTheme {
        Navigation()
    }
}

@Composable
@Preview
fun Navigation() {
    val navigationSuiteScaffoldState = rememberNavigationSuiteScaffoldState()
    var currentDestination by rememberSaveable { mutableStateOf(Destination.HOME) }

    NavigationSuiteScaffold(
        state = navigationSuiteScaffoldState,
        navigationSuiteItems = {
            Destination.entries.forEach { destination ->
                item(
                    selected = currentDestination == destination,
                    onClick = { currentDestination = destination },
                    icon = {
                        Icon(
                            painterResource(
                                if (currentDestination == destination) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                }
                            ),
                            contentDescription = stringResource(destination.title)
                        )
                    },
                    label = { Text(destination.name) }
                )
            }
        }
    ) {
        Button(onClick = {}) {
            Text("Hello World!")
        }
    }
}

enum class Destination(
    val title: StringResource,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource
) {
    HOME(Res.string.menu_home, Res.drawable.ic_home, Res.drawable.ic_home_filled),
    SHELF(Res.string.menu_shelf, Res.drawable.ic_shelf, Res.drawable.ic_shelf_filled),
    SETTINGS(Res.string.menu_settings, Res.drawable.ic_settings, Res.drawable.ic_settings_filled)
}
