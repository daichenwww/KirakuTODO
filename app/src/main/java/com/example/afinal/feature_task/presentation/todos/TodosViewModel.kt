package com.example.afinal.feature_task.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.common.util.getMY
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.use_case.todo.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TodosState())
    val state: State<TodosState> = _state

    private var getTodosJob: Job? = null

    init {
        getTodos()
    }

    private fun getTodos() {
        getTodosJob?.cancel()
        getTodosJob = todoUseCases.getTodos()
            .onEach { todos ->
                _state.value = state.value.copy(
                    todos = todos,
                    groupByMY = todos.groupBy{ getMY(it.dueDate) }
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: TodosEvent) {
        when(event){
            is TodosEvent.DoneTodo -> {
                if(event.todoId != null)
                {
                    viewModelScope.launch {
                        todoUseCases.addTodo(
                            Todo(
                                title = todoUseCases.getTodo(event.todoId)!!.title,
                                dueDate = todoUseCases.getTodo(event.todoId)!!.dueDate,
                                color = todoUseCases.getTodo(event.todoId)!!.color,
                                id = todoUseCases.getTodo(event.todoId)!!.id,
                                autoPlan = todoUseCases.getTodo(event.todoId)!!.autoPlan,
                                taskId = todoUseCases.getTodo(event.todoId)!!.taskId,
                                esTimeCost = todoUseCases.getTodo(event.todoId)!!.esTimeCost,
                                done = true
                            )
                        )
                    }
                }
            }
            is TodosEvent.CancelTodo -> {
                if(event.todoId != null)
                {
                    viewModelScope.launch {
                        todoUseCases.addTodo(
                            Todo(
                                title = todoUseCases.getTodo(event.todoId)!!.title,
                                dueDate = todoUseCases.getTodo(event.todoId)!!.dueDate,
                                color = todoUseCases.getTodo(event.todoId)!!.color,
                                id = todoUseCases.getTodo(event.todoId)!!.id,
                                autoPlan = todoUseCases.getTodo(event.todoId)!!.autoPlan,
                                taskId = todoUseCases.getTodo(event.todoId)!!.taskId,
                                esTimeCost = todoUseCases.getTodo(event.todoId)!!.esTimeCost,
                                done = false
                            )
                        )
                    }
                }
            }
        }
    }
}