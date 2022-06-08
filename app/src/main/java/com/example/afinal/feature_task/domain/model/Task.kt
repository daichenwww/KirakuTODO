package com.example.afinal.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.ui.theme.*

// If you change ANY column in entity, remember to clean up the original database.
// 1. Add .fallbackToDestructiveMigration() in file: AppModule before .build of database
// 2. Update the database version in file: TaskDatabase ex. 2->3
// 3. After you've run once, remove the .fallbackToDestructiveMigration() code.
//    Otherwise it will clean up the database EVERYTIME

@Entity
data class Task(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val dueDate: String, //TODO: change back to int (remember to clean database)
    // val doneDate: Int =0,
    val color: Int, // The content of tag is stored somewhere else and can be redefined.
    // val autoPlan: Boolean = true,
    // val esTimeCost: String ="" //30 min, 1hr, 2hr...
) {
    companion object{
        val taskColors = listOf(RedTag, YellowTag, GreenTag, BlueTag, PurpleTag)
    }
}

class InvalidTaskException(message: String): Exception(message)