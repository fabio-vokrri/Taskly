package it.fabiovokrri.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.model.TagEntity
import it.fabiovokrri.database.model.TaskEntity
import it.fabiovokrri.database.util.StatusConverter

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
@TypeConverters(StatusConverter::class)
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