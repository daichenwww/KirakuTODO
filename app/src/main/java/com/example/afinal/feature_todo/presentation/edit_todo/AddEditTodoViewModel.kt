package com.example.afinal.feature_todo.presentation.edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.common.TextFieldState
import com.example.afinal.common.util.getCurDate
import com.example.afinal.feature_task.domain.model.InvalidTaskException
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_todo.domain.model.InvalidTodoException
import com.example.afinal.feature_todo.domain.model.Todo
import com.example.afinal.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoTitle = mutableStateOf(
        TextFieldState(hint = "輸入標題")
    )
    val todoTitle: State<TextFieldState> = _todoTitle

    private val _todoDueDate = mutableStateOf(getCurDate())
    val todoDueDate: State<String> = _todoDueDate

    //TODO: choose the most frequently used?
    private val _todoColor = mutableStateOf(Task.taskColors.random().toArgb())
    val todoColor: State<Int> = _todoColor

    private val _todoPlan = mutableStateOf(true)
    val todoPlan: State<Boolean> = _todoPlan

    private val _todoDone = mutableStateOf(false)
    val todoDone: State<Boolean> = _todoDone

    // TODO: choose the average value for all data
    private val _todoEsTime = mutableStateOf(1)
    val todoEsTime: State<Int> = _todoEsTime

    private val _todoPlanDate = mutableStateOf(getCurDate())
    val todoPlanDate: State<String> = _todoPlanDate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            if(todoId != -1) { // -1 is the default value for new task
                viewModelScope.launch {
                    todoUseCases.getTodo(todoId)?.also { todo ->
                        currentTodoId = todo.todoId
                        _todoTitle.value = todoTitle.value.copy(
                            text = todo.todoTitle,
                            isHintVisible = false
                        )
                        _todoDueDate.value = todo.todoPlanDate
                        _todoColor.value = todo.todoColor
                        _todoPlan.value = todo.todoAutoPlan
                        _todoPlanDate.value = todo.todoPlanDate
                        _todoEsTime.value = todo.todoTime
                        _todoDone.value = todo.todoDone
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when(event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _todoTitle.value = todoTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeTitleFocus -> {
                _todoTitle.value = todoTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            todoTitle.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.ChangeDueDate -> {
                _todoDueDate.value = event.dueDate
            }
            is AddEditTodoEvent.ChangeColor -> {
                _todoColor.value = event.color
            }
            is AddEditTodoEvent.ChangeAutoPlan -> {
                _todoPlan.value = event.autoPlan
            }
            is AddEditTodoEvent.ChangePlanDate -> {
                _todoPlanDate.value = event.planDate
            }
            is AddEditTodoEvent.ChangeEsTime -> {
                _todoEsTime.value = event.esTime
            }
            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todoUseCases.addTodo(
                            Todo(
                                todoTitle = todoTitle.value.text,
                                todoColor = todoColor.value,
                                todoId = currentTodoId,
                                todoAutoPlan = todoPlan.value,
                                todoPlanDate = todoDueDate.value,
                                todoTime = todoEsTime.value,
                                todoDone = todoDone.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTodo)
                    } catch(e: InvalidTodoException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save todo"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTodo: UiEvent()
    }
}

