package com.example.afinal.feature_task.presentation.accumulation

import androidx.lifecycle.ViewModel
import com.example.afinal.common.util.getCurDate
import com.example.afinal.common.util.shiftDate
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccumulationViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {
    // Get dates
    private val _CurDate = getCurDate()
    val CurDate = _CurDate

    private val _CurDateShift7 = shiftDate(_CurDate, 7)
    val CurDateShift7 = _CurDateShift7

    private val _CurDateShift14 = shiftDate(_CurDate, 14)
    val CurDateShift14 = _CurDateShift14

    private val _CurDateShift21 = shiftDate(_CurDate, 21)
    val CurDateShift21 = _CurDateShift21

    private val _CurDateShift28 = shiftDate(_CurDate, 28)
    val CurDateShift28 = _CurDateShift28

    // Get done number in weeks
    private val _DoneTaskNumIn7 = taskUseCases.getDoneTaskNumberInRange(_CurDate, _CurDateShift7)
    val DoneTaskNumIn7 = _DoneTaskNumIn7

    private val _DoneTaskNumIn14 = taskUseCases.getDoneTaskNumberInRange(_CurDate, _CurDateShift14)
    val DoneTaskNumIn14 = _DoneTaskNumIn14

    private val _DoneTaskNumIn21 = taskUseCases.getDoneTaskNumberInRange(_CurDate, _CurDateShift21)
    val DoneTaskNumIn21 = _DoneTaskNumIn21

    private val _DoneTaskNumIn28 = taskUseCases.getDoneTaskNumberInRange(_CurDate, _CurDateShift28)
    val DoneTaskNumIn28 = _DoneTaskNumIn28

}