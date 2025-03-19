package it.fabiovokrri.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.fabiovokrri.data.repository.OfflineFirstTagsRepository
import it.fabiovokrri.data.repository.OfflineFirstTasksRepository
import it.fabiovokrri.data.repository.TagsRepository
import it.fabiovokrri.data.repository.TasksRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindTaskRepository(
        taskRepository: OfflineFirstTasksRepository,
    ): TasksRepository

    @Binds
    internal abstract fun bindTagRepository(
        tagRepository: OfflineFirstTagsRepository,
    ): TagsRepository
}