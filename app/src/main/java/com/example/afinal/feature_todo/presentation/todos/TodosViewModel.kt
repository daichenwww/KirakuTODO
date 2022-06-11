package com.example.afinal.feature_todo.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.feature_todo.domain.use_case.TodoUseCases
import com.example.afinal.feature_todo.presentation.todos.TodosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                    grouped = todos.groupBy{it.todoPlanDate}

                    //Find first item s.t. dateStr >= curDateStr
                )
            }
            .launchIn(viewModelScope)
    }
}