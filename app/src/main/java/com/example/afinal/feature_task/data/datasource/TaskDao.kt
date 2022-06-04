package com.example.afinal.feature_task.data.datasource

import androidx.room.*
import com.example.afinal.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Can be used as update
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}