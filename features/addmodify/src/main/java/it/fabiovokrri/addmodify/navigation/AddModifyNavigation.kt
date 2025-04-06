package it.fabiovokrri.addmodify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import it.fabiovokrri.addmodify.AddModifyScreen
import kotlinx.serialization.Serializable

@Serializable
data class AddModifyRoute(val id: Long? = null)

fun NavController.navigateToOverview(
    id: Long? = null,
    navOptions: NavOptions? = null,
) = navigate(AddModifyRoute(id), navOptions)

fun NavGraphBuilder.overviewScreen(
    onBackClick: () -> Unit,
) {
    composable<AddModifyRoute> {
        AddModifyScreen(
            onBackClick = onBackClick
        )
    }
}