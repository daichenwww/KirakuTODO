package com.example.afinal.common.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object AddTaskScreen: Screen("add_task_screen")
    object EditTaskScreen: Screen("edit_task_screen")
    object TodosScreen: Screen("todos_screen")
    object AddTodoScreen: Screen("add_todo_screen")
    object EditTodoScreen: Screen("edit_todo_screen")
}