package com.example.afinal.feature_task.presentation.accumulation

import android.text.TextUtils.substring
import androidx.lifecycle.ViewModel
import com.example.afinal.common.util.getCurDate
import com.example.afinal.common.util.shiftDate
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.use_case.todo.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class AccumulationViewModel @Inject constructor(
    private val taskUseCases: TodoUseCases
) : ViewModel() {
    // Get dates

    private val _CurDate = getCurDate()
    val CurDate = _CurDate.substring(IntRange(5,9))

    private val _CurDateShift7 = shiftDate(_CurDate, -6)
    val CurDateShift7 = _CurDateShift7.substring(IntRange(5,9))

    private val _CurDateShift14 = shiftDate(_CurDate, -13)
    val CurDateShift14 = _CurDateShift14.substring(IntRange(5,9))

    private val _CurDateShift21 = shiftDate(_CurDate, -20)
    val CurDateShift21 = _CurDateShift21.substring(IntRange(5,9))

    private val _CurDateShift28 = shiftDate(_CurDate, -27)
    val CurDateShift28 = _CurDateShift28.substring(IntRange(5,9))

    private var _doneTaskNumIn7 = 0
    private var _doneTaskNumIn14 = 0
    private var _doneTaskNumIn21 = 0
    private var _doneTaskNumIn28 = 0

    var doneTaskNumIn7 = 0
    var doneTaskNumIn14 = 0
    var doneTaskNumIn21 = 0
    var doneTaskNumIn28 = 0
    var maxDoneInMonth = 0



    init{
        _doneTaskNumIn7 = _doneTaskNumIn7()
        doneTaskNumIn7 = _doneTaskNumIn7

        _doneTaskNumIn14 = _doneTaskNumIn14()
        doneTaskNumIn14 = _doneTaskNumIn14

        _doneTaskNumIn21 = _doneTaskNumIn21()
        doneTaskNumIn21 = _doneTaskNumIn21

        _doneTaskNumIn28 = _doneTaskNumIn28()
        doneTaskNumIn28 = _doneTaskNumIn28

        maxDoneInMonth = max(doneTaskNumIn7, max(doneTaskNumIn14, max(doneTaskNumIn21, doneTaskNumIn28)))
    }

    suspend fun getDoneTaskNumIn7(): Deferred<Int> = withContext(Dispatchers.Default){
        async {
            // 今天 七天前
            taskUseCases.getDoneTodoNumberInRange(_CurDate, _CurDateShift7)
        }
    }
    suspend fun getDoneTaskNumIn14(): Deferred<Int> = withContext(Dispatchers.Default){
        async {
            taskUseCases.getDoneTodoNumberInRange(_CurDateShift7, _CurDateShift14)
        }
    }
    suspend fun getDoneTaskNumIn21(): Deferred<Int> = withContext(Dispatchers.Default){
        async {
            taskUseCases.getDoneTodoNumberInRange(_CurDateShift14, _CurDateShift21)
        }
    }
    suspend fun getDoneTaskNumIn28(): Deferred<Int> = withContext(Dispatchers.Default){
        async {
            taskUseCases.getDoneTodoNumberInRange(_CurDateShift21, _CurDateShift28)
        }
    }

    fun _doneTaskNumIn7(): Int = runBlocking {
        getDoneTaskNumIn7().await()

    }

    fun _doneTaskNumIn14(): Int = runBlocking {
        getDoneTaskNumIn14().await()
    }

    fun _doneTaskNumIn21(): Int = runBlocking {
        getDoneTaskNumIn21().await()
    }

    fun _doneTaskNumIn28(): Int = runBlocking {
        getDoneTaskNumIn28().await()
    }

}