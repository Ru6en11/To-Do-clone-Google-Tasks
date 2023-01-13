package com.example.googletasksclone.model.task

import androidx.lifecycle.LiveData
import com.example.googletasksclone.base.model.Repository

interface TaskRepository : Repository {

    fun getTasks(): LiveData<List<Task>>

    fun updateTask(task: Task)

    fun removeTask(task: Task)

    fun add(task: Task)

    fun moveTask(from: Int, to: Int)

}