package com.example.googletasksclone.views.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googletasksclone.model.task.InDatabaseTaskRepository
import com.example.googletasksclone.model.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = taskRepository.getTasks()
    val completedTasks: LiveData<List<Task>> = taskRepository.getCompletedTasks()
    val favouriteTasks: LiveData<List<Task>> = taskRepository.getFavouriteTasks()

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

    fun createTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.add(task)
        }
    }

    fun initState(state: MutableList<Task>? = null) {
        if (state != null) {
            _tasks.value = state!!
            return
        }
    }


}