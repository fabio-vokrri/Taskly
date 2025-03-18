package it.fabiovokrri.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.fabiovokrri.database.TasklyDatabase
import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.dao.TaskDao

/**
 * Module that provides DAOs for the database.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun provideTaskDao(
        database: TasklyDatabase
    ): TaskDao = database.getTaskDao()

    @Provides
    fun provideTagDao(
        database: TasklyDatabase
    ): TagDao = database.getTagDao()
}