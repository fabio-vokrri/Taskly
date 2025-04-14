package it.fabiovokrri.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Task model
 */
@OptIn(ExperimentalUuidApi::class)
data class Task(
    val id: Uuid = Uuid.random(),
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val status: TaskStatus,
    val tags: List<Tag>,
)