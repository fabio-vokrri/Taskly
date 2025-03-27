package it.fabiovokrri.overview.viewmodel

import it.fabiovokrri.model.Task

sealed interface OverviewEvent {
    data class StartTask(val task: Task) : OverviewEvent
    data class CompleteTask(val task: Task) : OverviewEvent
    data class DeleteTask(val task: Task) : OverviewEvent
}