package com.example.googletasksclone.views.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googletasksclone.model.task.InDatabaseTaskRepository
import com.example.googletasksclone.model.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    val tasks = taskRepository.getTasks()

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(task)
        }
    }

    fun onMoveTask(from: Int, to: Int) {
        taskRepository.moveTask(from, to)
    }

    fun removeTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.removeTask(task)
        }
    }
}