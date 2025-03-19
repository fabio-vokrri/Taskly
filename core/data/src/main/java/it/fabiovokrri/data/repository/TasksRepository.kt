package it.fabiovokrri.data.repository

import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.model.TaskStatus
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the tasks.
 */
interface TasksRepository {
    /**
     * Gets all the tasks.
     */
    fun getTasks(): Flow<List<PopulatedTaskResource>>

    /**
     * Gets a task by its [id].
     */
    fun getTaskById(id: Long): Flow<PopulatedTaskResource>

    /**
     * Upserts the given [task].
     */
    fun upsertTask(task: PopulatedTaskResource)

    /**
     * Deletes the given [task].
     */
    fun deleteTask(task: PopulatedTaskResource)

    /**
     * Gets all the tasks by the given [tagId].
     */
    fun getTasksByTag(tagId: Long): Flow<List<PopulatedTaskResource>>

    /**
     * Gets all the tasks by the given [status].
     */
    fun getTasksByStatus(status: TaskStatus): Flow<List<PopulatedTaskResource>>

    /**
     * Gets all the tasks by the given [dueDate].
     */
    fun getTasksByDueDate(dueDate: Long): Flow<List<PopulatedTaskResource>>

    /**
     * Gets all the tasks by the given [priority].
     */
    fun getTasksByPriority(priority: Int): Flow<List<PopulatedTaskResource>>

    /**
     * Changes the status of the given [task] to the given [newStatus].
     */
    fun changeTaskStatus(task: PopulatedTaskResource, newStatus: TaskStatus)

    /**
     * Changes the priority of the given [task] to the given [newPriority].
     */
    fun changeTaskPriority(task: PopulatedTaskResource, newPriority: Int)
}