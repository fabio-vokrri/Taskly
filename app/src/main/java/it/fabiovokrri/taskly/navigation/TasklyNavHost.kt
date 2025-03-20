package it.fabiovokrri.taskly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import it.fabiovokrri.overview.navigation.OverviewRoute
import it.fabiovokrri.overview.navigation.overviewScreen
import it.fabiovokrri.profile.navigation.profileScreen
import it.fabiovokrri.tasklist.navigation.taskListScreen

@Composable
fun TasklyNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = OverviewRoute,
        modifier = modifier,
    ) {
        overviewScreen()
        taskListScreen()
        profileScreen()
    }
}