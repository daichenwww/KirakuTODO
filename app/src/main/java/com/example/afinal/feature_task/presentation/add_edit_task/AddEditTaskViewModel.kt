package com.example.afinal.feature_task.presentation.add_edit_task

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.common.TextFieldState
import com.example.afinal.feature_task.domain.model.InvalidTaskException
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import com.example.afinal.common.util.getCurDate
import com.example.afinal.feature_task.presentation.tasks.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskTitle = mutableStateOf(
        TextFieldState(hint = "輸入標題")
    )
    val taskTitle: State<TextFieldState> = _taskTitle

    private val _taskDueDate = mutableStateOf(getCurDate())
    val taskDueDate: State<String> = _taskDueDate

    //TODO: choose the most frequently used?
    private val _taskColor = mutableStateOf(Task.taskColors.random().toArgb())
    val taskColor: State<Int> = _taskColor

    private val _taskPlan = mutableStateOf(true)
    val taskPlan: State<Boolean> = _taskPlan

    private val _taskDone = mutableStateOf(false)
    val taskDone: State<Boolean> = _taskDone

    // TODO: choose the average value for that type
    // But if there's no tag, this is meaningless. We only have color now.
    // average of all task?
    private val _taskEsTime = mutableStateOf(1)
    val taskEsTime: State<Int> = _taskEsTime

    private val _taskPlanDate = mutableStateOf(getCurDate())
    val taskPlanDate: State<String> = _taskPlanDate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentTaskId: Int? = null

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
                        _taskDueDate.value = task.dueDate
                        _taskColor.value = task.color
                        _taskPlan.value = task.autoPlan
                        _taskPlanDate.value = task.planDate
                        _taskDone.value = task.done
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when(event) {
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
            is AddEditTaskEvent.ChangeDueDate -> {
                _taskDueDate.value = event.dueDate
            }
            is AddEditTaskEvent.ChangeColor -> {
                _taskColor.value = event.color
            }
            is AddEditTaskEvent.ChangeAutoPlan -> {
                _taskPlan.value = event.autoPlan
            }
            is AddEditTaskEvent.ChangePlanDate -> {
                _taskPlanDate.value = event.planDate
            }
            is AddEditTaskEvent.ChangeEsTime -> {
                _taskEsTime.value = event.esTime
            }
            is AddEditTaskEvent.DeleteTask -> {
                if(event.taskId != null)
                { viewModelScope.launch { taskUseCases.deleteTask(event.taskId)} }
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                dueDate = taskDueDate.value,
                                color = taskColor.value,
                                id = currentTaskId,
                                autoPlan = taskPlan.value,
                                planDate = taskPlanDate.value,
                                esTimeCost = taskEsTime.value,
                                done = taskDone.value
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