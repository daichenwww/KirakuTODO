package com.example.afinal.collection.data.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    /*
    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Can be used as update
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
    */
}