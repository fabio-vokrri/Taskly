package it.fabiovokrri.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.fabiovokrri.database.TasklyDatabase
import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.dao.TaskTagCrossRefDao

/**
 * Module that provides DAOs for the database.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    /**
     * Provides the [TaskDao] instance.
     */
    @Provides
    fun provideTaskDao(
        database: TasklyDatabase,
    ): TaskDao = database.getTaskDao()

    /**
     * Provides the [TagDao] instance.
     */
    @Provides
    fun provideTagDao(
        database: TasklyDatabase,
    ): TagDao = database.getTagDao()

    /**
     * Provides the [TaskTagCrossRefDao] instance.
     */
    fun provideTaskTagCrossRefDao(
        database: TasklyDatabase,
    ): TaskTagCrossRefDao = database.getTaskTagCrossRefDao()

}