package com.example.afinal.feature_task.presentation.stampbook

import androidx.lifecycle.ViewModel
import com.example.afinal.common.util.getCurDate
import com.example.afinal.common.util.shiftDate
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StampBookViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {
    private val _DoneTaskNum = taskUseCases.getDoneTaskNumber()
    val DoneTaskNum = _DoneTaskNum
}