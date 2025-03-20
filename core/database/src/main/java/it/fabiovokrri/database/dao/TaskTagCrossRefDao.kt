package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import it.fabiovokrri.database.model.TaskTagCrossRefEntity

/**
 * Data access object for [TaskTagCrossRefEntity] access.
 */
@Dao
interface TaskTagCrossRefDao {
    /**
     * Inserts the given [crossRefs] into the database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(crossRefs: List<TaskTagCrossRefEntity>)

    /**
     * Deletes a cross reference from the database given the [taskId].
     */
    @Delete
    suspend fun delete(taskId: Long)
}