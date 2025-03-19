package it.fabiovokrri.data.repository

import it.fabiovokrri.model.Tag
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the tags.
 */
interface TagsRepository {
    /**
     * Gets all the tags.
     */
    fun getTags(): Flow<List<Tag>>

    /**
     * Gets a tag by its [id].
     */
    fun getTagById(id: Long): Flow<Tag>

    /**
     * Upserts the given [tag].
     */
    fun upsertTag(tag: Tag)

    /**
     * Deletes the given [tag].
     */
    fun deleteTag(tag: Tag)
}