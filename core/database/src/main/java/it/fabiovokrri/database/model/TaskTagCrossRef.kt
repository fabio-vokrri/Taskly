package it.fabiovokrri.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Cross reference entity for the many-to-many relationship between tasks and tags.
 */
@Entity(
    tableName = "tasks_tags",
    primaryKeys = ["task_id", "tag_id"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tag_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(value = ["task_id"]),
        Index(value = ["tag_id"]),
    ]
)
data class TaskTagCrossRef(
    @ColumnInfo(name = "task_id")
    val taskId: Long,
    @ColumnInfo(name = "tag_id")
    val tagId: Long
)