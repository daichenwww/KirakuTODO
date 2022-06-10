package com.example.afinal.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.ui.theme.*

// If you change ANY column in entity, remember to clean up the original database.
// 1. Add .fallbackToDestructiveMigration() in file: di.AppModule before .build of database
// 2. Update the database version in file: TaskDatabase ex. 2->3
// 3. After you've run once, remove the .fallbackToDestructiveMigration() code.
//    Otherwise it will clean up the database EVERYTIME

@Entity
data class Task(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val dueDate: String,
    val color: Int,
    val autoPlan: Boolean,
    val esTimeCost: Int, //unit: hr (1hr~8hr)
    val planDate: String, // for autoPlan off
    val done: Boolean
    // when autoPlan is off, there's no guarantee that esTimeCost =0
    // likewise, when autoPlan is on, there's no guarantee about planDate's value
) {
    companion object{
        val taskColors = listOf(RedTag, YellowTag, GreenTag, BlueTag, PurpleTag)
    }
}

class InvalidTaskException(message: String): Exception(message)