package com.example.kiraku_todo_owo.feature_note.data.repository

import com.example.kiraku_todo_owo.feature_note.data.data_source.TaskDao
import com.example.kiraku_todo_owo.feature_note.domain.model.Task
import com.example.kiraku_todo_owo.feature_note.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
): TaskRepository {
    override fun getTasks(): Flow<List<Task>>{
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task?{
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task){
        return dao.insertTask(task)
    }

    override suspend fun updateTask(task: Task){
        return dao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task){
        return dao.deleteTask(task)
    }

}