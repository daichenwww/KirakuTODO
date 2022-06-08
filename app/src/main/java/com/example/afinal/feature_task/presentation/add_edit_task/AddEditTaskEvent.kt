package com.example.afinal.feature_task.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent {
    data class EnteredTitle(val value: String): AddEditTaskEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditTaskEvent()
    data class EnteredDueDate(val value: String): AddEditTaskEvent()
    data class ChangeDueDateFocus(val focusState: FocusState): AddEditTaskEvent()
    data class ChangeColor(val color: Int): AddEditTaskEvent()
    object SaveTask: AddEditTaskEvent()
}