package com.example.afinal.feature_todo.data.datasource


import androidx.room.*
import com.example.afinal.feature_todo.domain.model.TaskWithTodos
import com.example.afinal.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface  TodoDao {

//    @Transaction
//    @Query("SELECT * FROM tasks_db.task")
//    fun getTodosByTask(): Flow<List<TaskWithTodos>>

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE todoId = :todoId")
    suspend fun getTodoById(todoId: Int): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("Delete FROM todo WHERE todoId = :todoId")
    suspend fun deleteTodo(todoId: Int)

}