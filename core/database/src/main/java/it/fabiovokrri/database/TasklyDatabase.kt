package it.fabiovokrri.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.model.TagEntity
import it.fabiovokrri.database.model.TaskEntity

/**
 * The [RoomDatabase] for Taskly.
 */
@Database(
    entities = [
        TaskEntity::class,
        TagEntity::class,
    ],
    version = 1,
    exportSchema = true
)
internal abstract class TasklyDatabase : RoomDatabase() {
    /**
     * Returns the [TaskDao] instance.
     */
    abstract fun getTaskDao(): TaskDao

    /**
     * Returns the [TagDao] instance.
     */
    abstract fun getTagDao(): TagDao
}