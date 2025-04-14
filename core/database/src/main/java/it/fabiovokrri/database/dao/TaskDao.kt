package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Data access object for [TaskEntity] access.
 */
@Dao
@OptIn(ExperimentalUuidApi::class)
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
    suspend fun getTaskById(id: String): PopulatedTaskResource

    @Upsert
    suspend fun upsertTask(task: TaskEntity): Uuid

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTask(id: String)
}