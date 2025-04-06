package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

typealias TaskId = Long

/**
 * Data access object for [TaskEntity] access.
 */
@Dao
interface TaskDao {
    /**
     * Gets all tasks from the database.
     */
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<PopulatedTaskResource>>

    /**
     * Gets a task by its [id].
     */
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): PopulatedTaskResource

    @Upsert
    suspend fun upsertTask(task: TaskEntity): TaskId

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTask(id: Long)
}