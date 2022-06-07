package com.example.kiraku_todo_owo.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.ui.theme.*


@Entity
class Task(
    val title: String,
    val dueDay: Int,
    val tag: String,
    val esTimeCost: Int,
    val autoSchedule: Int,
    val todo: Todo,
    @PrimaryKey val id: Int? = null
){
    /**
     *use task color to define tag?
    */
    companion object{
        val taskColors = listOf(MarkedPurple, MarkedBlue, MarkedGreen, MarkedYellow, MarkedRed)
    }
}

class InvalidTaskException(message: String): Exception(message)