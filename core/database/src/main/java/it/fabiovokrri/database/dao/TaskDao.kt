package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Query
import it.fabiovokrri.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for [TaskEntity] access.
 */
@Dao
interface TaskDao {
    /**
     * Gets all tasks from the database.
     */
    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>

    /**
     * Gets a task by its [id].
     */
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Long): Flow<TaskEntity>

    /**
     * Gets all incomplete tasks from the database.
     */
    @Query("SELECT * FROM tasks WHERE isCompleted = 0")
    fun getIncompleteTasks(): Flow<List<TaskEntity>>

    /**
     * Gets all completed tasks from the database.
     */
    @Query("SELECT * FROM tasks WHERE isCompleted = 1")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    /**
     * Gets tasks due to the specified [dueDate].
     */
    @Query("SELECT * FROM tasks WHERE dueDate = :dueDate")
    fun getTasksDueTo(dueDate: Long): Flow<List<TaskEntity>>

    /**
     * Gets tasks by the specified [priority].
     */
    @Query("SELECT * FROM tasks WHERE priority = :priority")
    fun getTasksByPriority(priority: Int): Flow<List<TaskEntity>>
}