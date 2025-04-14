package it.fabiovokrri.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import it.fabiovokrri.model.Task
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
            value = TaskTagCrossRefEntity::class,
            parentColumn = "task_id",
            entityColumn = "tag_id"
        )
    )
    val tags: List<TagEntity>,
)

/**
 * Converts a [PopulatedTaskResource] into a [Task].
 */
@OptIn(ExperimentalUuidApi::class)
fun PopulatedTaskResource.toExternalModel() = Task(
    id = Uuid.parse(task.id),
    title = task.title,
    description = task.description,
    dueDate = task.dueDate,
    priority = task.priority,
    tags = tags.map(TagEntity::toExternalModel),
    status = task.status
)
