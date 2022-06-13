package com.example.afinal.feature_task.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.common.util.getMY
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.use_case.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var getTasksJob: Job? = null

    init {
        getTasks()
    }

    private fun getTasks() {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks()
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                    groupByMY = tasks.groupBy{ getMY(it.dueDate) }
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: TasksEvent) {
        when(event){
            is TasksEvent.DoneTask -> {
                if(event.taskId != null)
                {
                    viewModelScope.launch {
                        taskUseCases.addTask(
                            Task(
                                title = taskUseCases.getTask(event.taskId)!!.title,
                                dueDate = taskUseCases.getTask(event.taskId)!!.dueDate,
                                color = taskUseCases.getTask(event.taskId)!!.color,
                                id = taskUseCases.getTask(event.taskId)!!.id,
                                autoPlan = taskUseCases.getTask(event.taskId)!!.autoPlan,
                                planDate = taskUseCases.getTask(event.taskId)!!.planDate,
                                esTimeCost = taskUseCases.getTask(event.taskId)!!.esTimeCost,
                                done = true
                            )
                        )
                    }
                }
            }
            is TasksEvent.CancelTask -> {
                if(event.taskId != null)
                {
                    viewModelScope.launch {
                        taskUseCases.addTask(
                            Task(
                                title = taskUseCases.getTask(event.taskId)!!.title,
                                dueDate = taskUseCases.getTask(event.taskId)!!.dueDate,
                                color = taskUseCases.getTask(event.taskId)!!.color,
                                id = taskUseCases.getTask(event.taskId)!!.id,
                                autoPlan = taskUseCases.getTask(event.taskId)!!.autoPlan,
                                planDate = taskUseCases.getTask(event.taskId)!!.planDate,
                                esTimeCost = taskUseCases.getTask(event.taskId)!!.esTimeCost,
                                done = false
                            )
                        )
                    }
                }
            }
        }
    }
}