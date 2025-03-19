package it.fabiovokrri.model

/**
 * Task model
 */
data class Task(
    val id: Long = 0,
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val status: TaskStatus,
    val tags: List<Tag>,
)