package com.example.googletasksclone.model.task

import com.example.googletasksclone.base.model.Repository

interface TaskRepository : Repository {

    fun getTasks(): List<Task>

    fun updateTask(task: Task)

    fun removeTask(task: Task)

    fun add(task: Task)

}