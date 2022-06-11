package com.example.afinal.feature_task.presentation.stampbook

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.feature_task.domain.use_case.TaskUseCases
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_task.presentation.tasks.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class StampBookViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private var _doneTaskNum = 0
    private var _page = ""

    var doneTaskNum = 0
    init {
        _doneTaskNum = myFunction()
        doneTaskNum = _doneTaskNum
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