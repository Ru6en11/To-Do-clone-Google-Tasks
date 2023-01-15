package com.example.googletasksclone.model.task

import androidx.lifecycle.LiveData
import com.example.googletasksclone.base.model.Repository

interface TaskRepository : Repository {

    fun getTasks(): LiveData<List<Task>>

    suspend fun updateTask(task: Task)

    suspend fun removeTask(task: Task)

    suspend fun add(task: Task)

    fun moveTask(from: Int, to: Int)

}