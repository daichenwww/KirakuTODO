package com.example.afinal.feature_task.domain.use_case.todo;

class TodoUseCases (
    val getTodo: GetTodo,
    val getTodos: GetTodos,
    val addTodo: AddTodo,
    val deleteTodo: DeleteTodo,
    val deleteTodoByTaskId: DeleteTodoByTaskId,
    val getTimeByDate: GetTimeByDate,
//    val addTodoByTask: AddTodoByTask,
    val getDoneTodoNumber: GetDoneTodoNumber,
    val getDoneTodoNumberInRange: GetDoneTodoNumberInRange
)
