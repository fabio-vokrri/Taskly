package it.fabiovokrri.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import it.fabiovokrri.model.Task

/**
 * Resource that contains the task and its tags.
 */
data class PopulatedTaskResource(
    @Embedded
    val task: TaskEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskTagCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "tag_id"
        )
    )
    val tags: List<TagEntity>
)

/**
 * Converts a [PopulatedTaskResource] into a [Task].
 */
fun PopulatedTaskResource.toExternalModel() = Task(
    id = task.id,
    title = task.title,
    description = task.description,
    dueDate = task.dueDate,
    priority = task.priority,
    tags = tags.map(TagEntity::toExternalModel),
    status = task.status
)
