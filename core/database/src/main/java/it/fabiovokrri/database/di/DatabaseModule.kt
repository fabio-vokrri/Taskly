package it.fabiovokrri.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.fabiovokrri.database.TasklyDatabase
import javax.inject.Singleton

/**
 * Module that provides the database instance.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideTasklyDatabase(
        @ApplicationContext context: Context,
    ): TasklyDatabase = Room.databaseBuilder(
        context,
        TasklyDatabase::class.java,
        "taskly-database"
    ).build()
}