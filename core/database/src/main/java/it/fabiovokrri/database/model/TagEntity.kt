package it.fabiovokrri.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import it.fabiovokrri.model.Tag

/**
 * Entity that represents a tag in the database.
 */
@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)

/**
 * Converts a [TagEntity] into a [Tag].
 */
fun TagEntity.toExternalModel() = Tag(
    name = name,
    id = id,
)

/**
 * Converts a [Tag] into a [TagEntity].
 */
fun Tag.toEntity() = TagEntity(
    name = name,
    id = id,
)