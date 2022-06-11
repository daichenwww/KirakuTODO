package com.example.afinal.di

import android.app.Application
import androidx.room.Room
import com.example.afinal.feature_task.data.datasource.TaskDatabase
import com.example.afinal.feature_task.data.repository.TaskRepositoryImpl
import com.example.afinal.feature_task.domain.repository.TaskRepository
import com.example.afinal.feature_task.domain.use_case.*
import com.example.afinal.feature_todo.data.datasource.TodoDatabase
import com.example.afinal.feature_todo.data.repository.TodoRepositoryImpl
import com.example.afinal.feature_todo.domain.repository.TodoRepository
import com.example.afinal.feature_todo.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
            getTask = GetTask(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            getTodo = GetTodo(repository),
            getTodos = GetTodos(repository),
            addTodo = AddTodo(repository),
            deleteTodo = DeleteTodo(repository)
        )
    }
}