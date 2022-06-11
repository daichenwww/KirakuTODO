package com.example.afinal.feature_todo.presentation.edit_todo

import androidx.compose.ui.focus.FocusState

sealed class AddEditTodoEvent {
    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditTodoEvent()
    data class ChangeDueDate(val dueDate:String): AddEditTodoEvent()
    data class ChangeColor(val color: Int): AddEditTodoEvent()
    data class ChangePlanDate(val planDate:String): AddEditTodoEvent()
    data class ChangeAutoPlan(val autoPlan: Boolean): AddEditTodoEvent()
    data class ChangeEsTime(val esTime: Int): AddEditTodoEvent()
    data class DeleteTodo(val taskId: Int?): AddEditTodoEvent()
    object SaveTodo: AddEditTodoEvent()
}