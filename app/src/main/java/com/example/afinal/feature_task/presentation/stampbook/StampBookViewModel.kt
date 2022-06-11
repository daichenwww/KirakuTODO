package com.example.afinal.feature_task.presentation.stampbook

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import com.example.afinal.feature_task.presentation.tasks.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class StampBookViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private var _doneTaskNum = 0

    var doneTaskNum = 0
    var page = ""
    init {
        _doneTaskNum = myFunction()
        doneTaskNum = _doneTaskNum
        page = "1"
    }

    fun nextPage(){
        if(page.toInt() < 3){
            val newPage = page.toInt() + 1
            page = newPage.toString()
        }
    }
    fun prevPage(){
        if(page.toInt() > 1){
            val newPage = page.toInt() - 1
            page = newPage.toString()
        }
    }

    fun myFunction(): Int = runBlocking {
        getDoneTaskNumber().await()
    }
    suspend fun getDoneTaskNumber(): Deferred<Int> = withContext(Dispatchers.Default){
        async {
            taskUseCases.getDoneTaskNumber()
        }
    }

}

/*
val job: Job = GlobalScope.launch(Dispatchers.Main) {
    // launch coroutine in the main thread
    for (i in 10 downTo 1) { // countdown from 10 to 1
        textView.text = "count down $i ..." // update text
        delay(1000) // wait half a second
    }
    textView.text = "Done!"
}

val rawText:Deferred<String> = myScope.async(Dispatchers.IO){
        "result"
    }
 */