@file:OptIn(ExperimentalUuidApi::class)

package it.fabiovokrri.database.model

import androidx.room.Entity
import it.fabiovokrri.model.Tag
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Entity that represents a tag in the database.
 */
@Entity(tableName = "tags")
data class TagEntity(
    val id: String,
    val name: String,
)

/**
 * Converts a [TagEntity] into a [Tag].
 */
fun TagEntity.toExternalModel() = Tag(
    name = name,
    id = Uuid.parse(id),
)

/**
 * Converts a [Tag] into a [TagEntity].
 */
fun Tag.toEntity() = TagEntity(
    name = name,
    id = id.toHexString(),
)