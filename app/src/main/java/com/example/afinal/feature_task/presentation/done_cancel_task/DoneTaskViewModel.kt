package com.example.afinal.feature_task.presentation.done_cancel_task

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
import com.example.afinal.feature_task.domain.use_case.task.TaskUseCases
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    fun onEvent(event:DoneTaskEvent) {
        when(event){
            is DoneTaskEvent.DoneTask -> {
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
            is DoneTaskEvent.CancelTask -> {
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