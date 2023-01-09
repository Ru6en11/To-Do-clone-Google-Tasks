package com.example.googletasksclone.views.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.googletasksclone.model.task.InDatabaseTaskRepository
import com.example.googletasksclone.model.task.Task
import com.example.googletasksclone.model.task.Subscriber

class TasksViewModel : ViewModel(), TasksListener, Subscriber {

    private val taskRepository = InDatabaseTaskRepository.get()

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        taskRepository.addSubscriber(this)
    }

    override fun onCleared() {
        super.onCleared()
        taskRepository.removeSubscriber(this)
    }

    override fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    override fun removeTask(task: Task) {
        taskRepository.removeTask(task)
    }

    fun createTask(task: Task) {
        taskRepository.add(task)
    }

    override fun setChanges(tasks: List<Task>) {
        _tasks.value = tasks
    }

    fun initState(state: MutableList<Task>? = null) {
        if (state != null) {
            _tasks.value = state!!
            return
        }
        taskRepository.add(Task(true, "Task 0","addit info", true))
        for (i in 1..10) {
            taskRepository.add(
                Task(false, "Task $i", "" , false)
            )
        }
    }


}