package it.fabiovokrri.taskly.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import it.fabiovokrri.overview.navigation.OverviewBaseRoute
import it.fabiovokrri.overview.navigation.OverviewRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    OVERVIEW(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = 1,
        titleTextId = 1,
        route = OverviewRoute::class,
        baseRoute = OverviewBaseRoute::class,
    ),

    TASKLIST(
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        iconTextId = 1,
        titleTextId = 1,
        route = OverviewRoute::class,
        baseRoute = OverviewBaseRoute::class,
    ),

    PROFILE(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = 1,
        titleTextId = 1,
        route = OverviewRoute::class,
        baseRoute = OverviewBaseRoute::class,
    )
}