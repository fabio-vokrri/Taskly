package it.fabiovokrri.addmodify.viewmodel

import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.TaskStatus

data class TaskState(
    val title: String = "",
    val description: String = "",
    val tags: List<Tag> = emptyList(),
    val status: TaskStatus = TaskStatus.NOT_STARTED,
    val priority: Int = 0,
    val dueDate: Long = System.currentTimeMillis(),
)