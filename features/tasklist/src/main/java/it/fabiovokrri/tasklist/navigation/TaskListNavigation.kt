package it.fabiovokrri.tasklist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import it.fabiovokrri.tasklist.TaskListScreen
import kotlinx.serialization.Serializable

@Serializable
data object TaskListRoute

@Serializable
data object TaskListBaseRoute

fun NavController.navigateToTaskList(navOptions: NavOptions? = null) =
    navigate(TaskListRoute, navOptions)

fun NavGraphBuilder.taskListScreen() {
    navigation<TaskListBaseRoute>(startDestination = TaskListRoute) {
        composable<TaskListRoute> {
            TaskListScreen()
        }
    }
}