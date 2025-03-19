package it.fabiovokrri.database.util

import androidx.room.TypeConverter
import it.fabiovokrri.model.TaskStatus

internal class StatusConverter {
    @TypeConverter
    fun fromStatus(status: TaskStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(status: String): TaskStatus {
        return TaskStatus.valueOf(status)
    }
}