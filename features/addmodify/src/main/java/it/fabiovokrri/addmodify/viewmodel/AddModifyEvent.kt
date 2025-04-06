package it.fabiovokrri.addmodify.viewmodel

import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.TaskStatus

sealed interface AddModifyEvent {
    data class ChangeTitle(val title: String) : AddModifyEvent
    data class ChangeDescription(val description: String) : AddModifyEvent
    data class AddTag(val tag: Tag) : AddModifyEvent
    data class RemoveTag(val tag: Tag) : AddModifyEvent
    data class ChangeStatus(val status: TaskStatus) : AddModifyEvent
    data class ChangePriority(val priority: Int) : AddModifyEvent
    data class ChangeDueDate(val dueDate: Long) : AddModifyEvent
    data object Save : AddModifyEvent
}