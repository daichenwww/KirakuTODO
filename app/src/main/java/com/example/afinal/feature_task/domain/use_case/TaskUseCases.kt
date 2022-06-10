package com.example.afinal.feature_task.domain.use_case

class TaskUseCases (
    val getTasks: GetTasks,
    val getTask: GetTask,
    val addTask: AddTask, // can be used as update
    val deleteTask: DeleteTask, // remember to add this comma
    // edited by: zshzzz
    val getDoneTaskNumberInRange: GetDoneTaskNumberInRange,
    val getDoneTaskNumber: GetDoneTaskNumber
)