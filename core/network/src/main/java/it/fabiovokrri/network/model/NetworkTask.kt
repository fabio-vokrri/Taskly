package it.fabiovokrri.network.model

import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class NetworkTask(
    val id: String,
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val status: String,
    val tags: List<String>,
)

@OptIn(ExperimentalUuidApi::class)
fun NetworkTask.asExternalModel(): Task = Task(
    Uuid.parse(id),
    title,
    description,
    dueDate,
    priority,
    TaskStatus.valueOf(status),
    listOf() // TODO
)