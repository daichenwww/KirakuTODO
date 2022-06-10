package com.example.afinal.feature_todo

data class Todo(
    var title: String,
    var planDay: Int,
    var tag: Int,
    var taskTime: Int,
    var todoTime: Int,
    var done: Boolean
)