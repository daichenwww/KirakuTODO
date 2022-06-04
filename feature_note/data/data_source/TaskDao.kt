package com.example.kiraku_todo_owo.feature_note.data.data_source
import androidx.room.*
import com.example.kiraku_todo_owo.feature_note.domain.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * AddTask, DeleteTask, EditTask, GetTask, GetTasks
 */

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)

}