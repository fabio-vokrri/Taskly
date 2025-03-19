package it.fabiovokrri.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus

/**
 * Entity that represents a task in the database.
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val status: TaskStatus
)

/**
 * Converts a [TaskEntity] into an [Task].
 */
fun TaskEntity.toExternalModel() = Task(
    id = id,
    title = title,
    description = description,
    dueDate = dueDate,
    priority = priority,
    tags = emptyList(),
    status = status
)