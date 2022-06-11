package com.example.afinal.feature_todo.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.afinal.feature_task.domain.model.Task

@Entity
data class Todo(
    @PrimaryKey val todoId: Int? = null,
    val todoTitle: String,
    val todoColor: Int,
    val todoAutoPlan: Boolean,
    val todoPlanDate: String, // for autoPlan off
    val todoTime: Int, //unit: hr (1hr~8hr)
    val todoDone: Boolean
    // when autoPlan is off, there's no guarantee that esTimeCost =0
    // likewise, when autoPlan is on, there's no guarantee about planDate's value
)
data class TaskWithTodos(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "todoId"
    )
    val todosOfTask: List<Todo>
)

class InvalidTodoException(message: String): Exception(message)