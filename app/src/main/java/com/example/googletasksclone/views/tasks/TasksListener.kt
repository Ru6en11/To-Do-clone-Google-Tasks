package com.example.googletasksclone.views.tasks

import com.example.googletasksclone.model.task.Task

interface TasksListener {

    fun updateTask(task: Task)

    fun removeTask(task: Task)

}