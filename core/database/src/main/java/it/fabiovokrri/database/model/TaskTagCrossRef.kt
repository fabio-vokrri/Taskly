package it.fabiovokrri.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Cross reference entity for the many-to-many relationship between tasks and tags.
 */
@Entity(
    tableName = "task_tags",
    primaryKeys = ["taskId", "tagId"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tagId"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(value = ["taskId"]),
        Index(value = ["tagId"]),
    ]
)
data class TaskTagCrossRef(
    val taskId: Long,
    val tagId: Long
)