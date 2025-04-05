package it.fabiovokrri.overview.viewmodel

import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.Task

sealed interface OverviewEvent {
    data class StartTask(val task: Task) : OverviewEvent
    data class CompleteTask(val task: Task) : OverviewEvent
    data class DeleteTask(val task: Task) : OverviewEvent
    data class ToggleTagSelection(val tagId: Long) : OverviewEvent
    data class UpsertTag(val tag: Tag) : OverviewEvent
}