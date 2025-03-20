package it.fabiovokrri.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import it.fabiovokrri.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

@Serializable
data object ProfileBaseRoute

fun NavController.navigateToProfile(navOptions: NavOptions? = null) =
    navigate(ProfileRoute, navOptions)

fun NavGraphBuilder.profileScreen() {
    navigation<ProfileBaseRoute>(startDestination = ProfileRoute) {
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}