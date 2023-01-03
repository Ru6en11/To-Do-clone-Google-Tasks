package com.example.googletasksclone

interface TasksListener {

    fun updateTask(task: Task)

    fun removeTask(task: Task)

}