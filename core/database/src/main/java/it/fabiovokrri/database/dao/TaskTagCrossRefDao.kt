package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.fabiovokrri.database.model.TaskTagCrossRefEntity

/**
 * Data access object for [TaskTagCrossRefEntity] access.
 */
@Dao
interface TaskTagCrossRefDao {
    /**
     * Inserts the given [crossRef] into the database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(crossRef: TaskTagCrossRefEntity)

    /**
     * Inserts the given [crossRefs] into the database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(crossRefs: List<TaskTagCrossRefEntity>)

    /**
     * Deletes a cross reference from the database given the [taskId].
     */
    @Query("DELETE FROM tasks_tags WHERE task_id = :taskId")
    suspend fun deleteByTaskId(taskId: Long)

    /**
     * Deletes a cross reference from the database given the [tagId].
     */
    @Query("DELETE FROM tasks_tags WHERE tag_id = :tagId")
    suspend fun deleteByTagId(tagId: Long)
}