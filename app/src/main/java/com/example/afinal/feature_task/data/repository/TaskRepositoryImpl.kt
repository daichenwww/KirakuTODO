package com.example.afinal.feature_task.data.repository

import com.example.afinal.feature_task.data.datasource.TaskDao
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl (
    private val dao: TaskDao
    )  : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task): Long {
        return dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Int) {
        dao.deleteTask(task)
    }

}