package it.fabiovokrri.data.repository

import it.fabiovokrri.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the tasks.
 */
interface TasksRepository {
    /**
     * Gets all the tasks.
     */
    fun getTasks(): Flow<List<Task>>

    /**
     * Gets a task by its [id].
     */
    fun getTaskById(id: Long): Flow<Task>

    /**
     * Upserts the given [task].
     */
    suspend fun upsertTask(task: Task)

    /**
     * Deletes the given [task].
     */
    suspend fun deleteTask(task: Task)
}