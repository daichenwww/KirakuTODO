package com.example.afinal.feature_task.presentation.add_edit_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.feature_task.domain.model.InvalidTaskException
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskTitle = mutableStateOf(TextFieldState(
        hint = "Enter title..."
    ))
    val taskTitle: State<TextFieldState> = _taskTitle

    private val _taskDueDate = mutableStateOf(TextFieldState(
        hint = "Enter the due date..."
    ))
    val taskDueDate: State<TextFieldState> = _taskDueDate

    private val _taskColor = mutableStateOf(Task.taskColors.random().toArgb()) //TODO: choose the most frequently used?
    val taskColor: State<Int> = _taskColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if(taskId != -1) { // -1 is the default value for new task
                viewModelScope.launch {
                    taskUseCases.getTask(taskId)?.also { task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        _taskDueDate.value = _taskDueDate.value.copy(
                            text = task.dueDate,
                            isHintVisible = false
                        )
                        _taskColor.value = task.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when(event) {
            //TODO: add delete function
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredDueDate -> {
                _taskDueDate.value = _taskDueDate.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeDueDateFocus -> {
                _taskDueDate.value = _taskDueDate.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _taskDueDate.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.ChangeColor -> {
                _taskColor.value = event.color
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                dueDate = taskDueDate.value.text,
                                // timestamp = System.currentTimeMillis(),
                                color = taskColor.value,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch(e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}