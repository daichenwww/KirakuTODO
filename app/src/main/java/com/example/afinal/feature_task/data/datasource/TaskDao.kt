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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @Query("SELECT LAST_INSERT_ID()")
    suspend fun insertTask(task: Task): Long

    @Query("Delete FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)

}