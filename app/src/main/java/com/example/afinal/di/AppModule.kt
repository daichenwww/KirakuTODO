package com.example.afinal.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.afinal.feature_task.data.datasource.TaskDatabase
import com.example.afinal.feature_task.data.repository.TaskRepositoryImpl
import com.example.afinal.feature_task.domain.repository.TaskRepository
import com.example.afinal.feature_task.domain.use_case.*
import com.example.afinal.setting.data.datasource.UserPreferences
import com.example.afinal.setting.data.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Directly add new injection here
    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTask = DeleteTask(repository),
            addTask = AddTask(repository),
            getTask = GetTask(repository),
            // edited by: zshzzz
            getDoneTaskNumber = GetDoneTaskNumber(repository),
            getDoneTaskNumberInRange = GetDoneTaskNumberInRange(repository),
        )
    }


    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("setting")
            }
        )

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPreferencesStore:DataStore<Preferences>): UserPreferencesRepository {
        return UserPreferencesRepository(userPreferencesStore)
    }
}