@file:OptIn(ExperimentalUuidApi::class)

package it.fabiovokrri.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Entity that represents a task in the database.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    val id: String,
    val title: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val status: TaskStatus,
)

/**
 * Converts a [TaskEntity] into a [Task].
 */
fun TaskEntity.toExternalModel() = Task(
    id = Uuid.parse(id),
    title = title,
    description = description,
    dueDate = dueDate,
    priority = priority,
    tags = emptyList(),
    status = status
)

/**
 * Converts a [Task] into a [TaskEntity].
 */
fun Task.toEntity() = TaskEntity(
    id = id.toHexString(),
    title = title,
    description = description,
    dueDate = dueDate,
    priority = priority,
    status = status
)