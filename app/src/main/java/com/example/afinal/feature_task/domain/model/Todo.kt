package com.example.afinal.feature_task.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = CASCADE
        )
    ]
)
data class Todo(
    @PrimaryKey @ColumnInfo (name = "id") val id: Int? = null,
    @ColumnInfo (name = "task_id") val taskId: Int? = null,
    @ColumnInfo (name = "title") val title: String, // same as task
    @ColumnInfo (name = "dueDate") val dueDate: String, // plan day
    @ColumnInfo (name = "color") val color: Int,
    @ColumnInfo (name = "autoPlan") val autoPlan: Boolean,
    @ColumnInfo (name = "esTimeCost") val esTimeCost: Int,
    @ColumnInfo (name = "done") val done: Boolean
)

class InvalidTodoException(message: String): Exception(message)