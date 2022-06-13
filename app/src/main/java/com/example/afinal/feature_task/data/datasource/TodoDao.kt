package com.example.afinal.feature_task.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.afinal.feature_task.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE task_id = :taskId")
    suspend fun getTodosByTaskId(taskId: Int): List<Todo>?

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: Int)

    @Query("DELETE FROM todo WHERE task_id = :taskId")
    suspend fun deleteTodoByTaskId(taskId: Int)

    @Query("SELECT * FROM todo WHERE dueDate = :date")
    suspend fun getTodoByDate(date: String): List<Todo>?

    // edited by: zshzzz
    // 1. searchTaskInRange
    @Query("SELECT COUNT(*) FROM todo WHERE dueDate >= :start AND dueDate <= :end AND done=1")
    suspend fun getDoneTodoNumberInRange(end: String, start: String): Int

    // 2. find the task number which has been done
    @Query("SELECT COUNT(*) FROM todo WHERE done = 1")
    suspend fun getDoneTodoNumber(): Int
}