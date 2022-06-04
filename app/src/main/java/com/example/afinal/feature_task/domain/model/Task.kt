package com.example.afinal.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.ui.theme.*

@Entity
data class Task(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val dueDay: Int,
    val doneDay: Int,
    val tag: Int,
    val autoPlan: Boolean,
    val esTimeCost: Int,
) {
    companion object{
        val taskColors = listOf(RedTag, YellowTag, GreenTag, BlueTag, PurpleTag)
    }
}

class InvalidTaskException(message: String): Exception(message)