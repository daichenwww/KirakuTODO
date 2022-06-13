package com.example.afinal.feature_task.presentation.add_edit_task

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.common.TextFieldState
import com.example.afinal.feature_task.domain.model.InvalidTaskException
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.use_case.task.TaskUseCases
import com.example.afinal.common.util.getCurDate
import com.example.afinal.common.util.getDateName
import com.example.afinal.common.util.shiftDate
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.use_case.todo.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Integer.min
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
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

    // TODO: choose the average value for all data
    private val _taskEsTime = mutableStateOf(1)
    val taskEsTime: State<Int> = _taskEsTime

    private val _taskPlanDate = mutableStateOf(getCurDate())
    val taskPlanDate: State<String> = _taskPlanDate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentTaskId: Int? = null

    private val workTimeForWeekDay: Int = 3
    private val workTimeForWeenkend: Int = 8
    private val unitTime: Int = 2

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
                        _taskEsTime.value = task.esTimeCost
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
                // todo: todo也要刪掉 根據taskID刪
                if(event.taskId != null) {
                    viewModelScope.launch {
                        todoUseCases.deleteTodoByTaskId(event.taskId)
                        taskUseCases.deleteTask(event.taskId)
                    }
                }
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        val lastid = taskUseCases.addTask(
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
                        if (!taskPlan.value) { // user自己排
                            todoUseCases.addTodo(
                                Todo(
                                    title = taskTitle.value.text,
                                    dueDate = taskPlanDate.value,
                                    color = taskColor.value,
                                    taskId = lastid.toInt(),
                                    id = if (currentTaskId!=null) (currentTaskId!!*100) else null,
                                    autoPlan = taskPlan.value,
                                    esTimeCost = taskEsTime.value,
                                    done = taskDone.value,
                                    stamp = 1
                                )
                            )
                        }
                        else { // 排程!
                            var dateString: String = getCurDate()
                            var numberOfDate: Int = 0
                            var dates: MutableList<Pair<String, Long>> = arrayListOf()
                            while (dateString != shiftDate(taskDueDate.value, 1)) {
                                if (getDateName(dateString) == "Sat" || getDateName(dateString) == "Sun") {
                                    // 處理假日舒適工時較長
                                    dates.add(Pair(dateString, todoUseCases.getTimeByDate(dateString)-(workTimeForWeenkend-workTimeForWeekDay)))
                                }
                                else dates.add(Pair(dateString, todoUseCases.getTimeByDate(dateString)))
                                dateString = shiftDate(dateString, 1)
                                numberOfDate++
                            }
                            dates.sortBy {it.second}
                            var taskTime: Int = taskEsTime.value
                            var index: Int = 0
                            var idd: Int = 0
                            while (taskTime != 0) {
                                ++idd
                                var t: Int = min(taskTime, unitTime)
                                todoUseCases.addTodo(
                                    Todo(
                                        title = taskTitle.value.text,
                                        dueDate = dates[index].first,
                                        color = taskColor.value,
                                        taskId = lastid.toInt(),
                                        id = if (currentTaskId != null) (currentTaskId!!*100+idd) else null,
                                        autoPlan = taskPlan.value,
                                        esTimeCost = t,
                                        done = taskDone.value,
                                        stamp = 1
                                    )
                                )
                                taskTime -= t
                                Log.d("before copy, first: " + dates[index].first+ " second: "+ dates[index].second.toString(), "debug")
                                dates[index] = Pair(dates[index].first, dates[index].second+t)
                                Log.d("after copy, first :" + dates[index].first+ " second: " + dates[index].second.toString(),  "debug")
                                if (dates[index].second > workTimeForWeekDay){

                                }
                                if (index+1 == numberOfDate) {
                                    if (dates[index].second > dates[0].second) index = 0
                                    Log.d("index =" + index.toString(), "index??")
                                }
                                else if (dates[index].second > dates[index+1].second) {
                                    index += 1
                                    Log.d("index =" + index.toString(), "index??")
                                }
                            }
                        }

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

