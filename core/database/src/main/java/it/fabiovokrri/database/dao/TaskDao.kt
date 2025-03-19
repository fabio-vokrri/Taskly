package it.fabiovokrri.database.dao

import androidx.room.Dao
import androidx.room.Query
import it.fabiovokrri.database.model.TaskEntity
import it.fabiovokrri.model.TaskStatus
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
     * Gets all the tasks with the specified [status].
     */
    @Query("SELECT * FROM tasks WHERE status = :status")
    fun getByStatus(status: TaskStatus): Flow<List<TaskEntity>>

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