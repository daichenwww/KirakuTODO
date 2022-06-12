package com.example.afinal.feature_task.domain.use_case.task

class TaskUseCases (
    val getTasks: GetTasks,
    val getTask: GetTask,
    val addTask: AddTask, // can be used as update
    val deleteTask: DeleteTask,
)