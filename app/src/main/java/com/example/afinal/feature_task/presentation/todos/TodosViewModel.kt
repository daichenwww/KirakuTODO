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
                        val num = todoUseCases.getDoneTodoNumber()
                        val unlocked = getUnlockStamps(num)
                        todoUseCases.addTodo(
                            Todo(
                                title = todoUseCases.getTodo(event.todoId)!!.title,
                                dueDate = todoUseCases.getTodo(event.todoId)!!.dueDate,
                                color = todoUseCases.getTodo(event.todoId)!!.color,
                                id = todoUseCases.getTodo(event.todoId)!!.id,
                                autoPlan = todoUseCases.getTodo(event.todoId)!!.autoPlan,
                                taskId = todoUseCases.getTodo(event.todoId)!!.taskId,
                                esTimeCost = todoUseCases.getTodo(event.todoId)!!.esTimeCost,
                                done = true,
                                stamp = (0..unlocked).random()
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
                                done = false,
                                stamp = 1
                            )
                        )
                    }
                }
            }
        }
    }
}


fun getUnlockStamps(done:Int) :Int{
    when(done){
        0 -> return 0
        in 1..4 -> return 1
        in 5..9 -> return 2
        in 10..14 -> return 3
        in 15..19 -> return 4
        in 20..29 -> return 5
        in 30..49 -> return 6
        in 50..74 -> return 7
        in 75..99 -> return 8
        in 100..149 -> return 9
        in 150..199 -> return 10
        in 200..299 -> return 11
        in 300..499 -> return 12
        in 500..749 -> return 13
        in 750..999 -> return 14
        else -> return 15
    }

}