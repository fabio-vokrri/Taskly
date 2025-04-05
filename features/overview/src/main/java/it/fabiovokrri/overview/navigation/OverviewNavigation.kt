package it.fabiovokrri.overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import it.fabiovokrri.overview.OverviewScreen
import kotlinx.serialization.Serializable

@Serializable
data object OverviewRoute

@Serializable
data object OverviewBaseRoute

fun NavController.navigateToOverview(navOptions: NavOptions? = null) =
    navigate(OverviewRoute, navOptions)

fun NavGraphBuilder.overviewScreen() {
    navigation<OverviewBaseRoute>(startDestination = OverviewRoute) {
        composable<OverviewRoute> {
            OverviewScreen(onTaskClick = {})
        }
    }
}