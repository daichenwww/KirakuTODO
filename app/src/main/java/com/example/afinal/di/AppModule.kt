package com.example.afinal.di

import android.app.Application
import androidx.room.Room
import com.example.afinal.feature_task.data.datasource.KirakuDatabase
import com.example.afinal.feature_task.data.repository.TaskRepositoryImpl
import com.example.afinal.feature_task.data.repository.TodoRepositoryImpl
import com.example.afinal.feature_task.domain.repository.TaskRepository
import com.example.afinal.feature_task.domain.repository.TodoRepository
import com.example.afinal.feature_task.domain.use_case.task.*
import com.example.afinal.feature_task.domain.use_case.todo.*
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
    fun provide(app: Application): KirakuDatabase {
        return Room.databaseBuilder(
            app,
            KirakuDatabase::class.java,
            "kiraku_db"
        )/*.fallbackToDestructiveMigration()*/.build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: KirakuDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao())
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: KirakuDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao())
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
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            getTodos = GetTodos(repository),
            deleteTodo = DeleteTodo(repository),
            deleteTodoByTaskId = DeleteTodoByTaskId(repository),
            addTodo = AddTodo(repository),
            getTodo = GetTodo(repository),
            getTimeByDate = GetTimeByDate(repository),
            getDoneTodoNumber = GetDoneTodoNumber(repository),
            getDoneTodoNumberInRange = GetDoneTodoNumberInRange(repository)
        )
    }
}