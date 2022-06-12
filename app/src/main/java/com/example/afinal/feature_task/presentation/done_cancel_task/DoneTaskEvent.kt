package com.example.afinal.feature_task.presentation.done_cancel_task

import androidx.compose.ui.focus.FocusState

sealed class DoneTaskEvent {
    data class EnteredTitle(val value: String): DoneTaskEvent()
    data class ChangeTitleFocus(val focusState: FocusState): DoneTaskEvent()
    data class ChangeDueDate(val dueDate:String): DoneTaskEvent()
    data class ChangeColor(val color: Int): DoneTaskEvent()
    data class ChangePlanDate(val planDate:String): DoneTaskEvent()
    data class ChangeAutoPlan(val autoPlan: Boolean): DoneTaskEvent()
    data class ChangeEsTime(val esTime: Int): DoneTaskEvent()
    data class DoneTask(val taskId: Int?): DoneTaskEvent()
    data class CancelTask(val taskId: Int?): DoneTaskEvent()
    object SaveTask: DoneTaskEvent()
}