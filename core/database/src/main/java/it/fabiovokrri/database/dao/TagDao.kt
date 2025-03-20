package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import it.fabiovokrri.database.model.TagEntity
import kotlinx.coroutines.flow.Flow


/**
 * Data access object for [TagEntity] access.
 */
@Dao
interface TagDao {
    /**
     * Gets all tags from the database.
     */
    @Query("SELECT * FROM tags")
    fun getTags(): Flow<List<TagEntity>>

    /**
     * Gets a tag by its [id].
     */
    @Query("SELECT * FROM tags WHERE id = :id")
    fun getTagById(id: Long): Flow<TagEntity>

    /**
     * Inserts or updates a tag in the database.
     */
    @Upsert
    suspend fun upsertTag(tag: TagEntity)

    /**
     * Deletes a tag from the database.
     */
    @Query("DELETE FROM tags WHERE id = :id")
    suspend fun deleteTag(id: Long)

    /**
     * Gets all the tags associated with the given [taskId].
     */
    @Query("SELECT * FROM tags JOIN tasks_tags ON tags.id = tasks_tags.tag_id WHERE tasks_tags.task_id = :taskId")
    fun getTagsByTaskId(taskId: Long): Flow<List<TagEntity>>
}