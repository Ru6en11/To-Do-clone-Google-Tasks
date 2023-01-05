package com.example.googletasksclone.model.task

class TaskRepository {

    val tasks =  mutableListOf<Task>()

    fun updateTask(task: Task) {

    }

    fun removeTask(task: Task) {

    }

    fun add(task: Task) {

    }

    companion object {

        private var INSTANCE: TaskRepository? = null

        fun get(): TaskRepository {
            return INSTANCE ?: throw IllegalStateException("TaskRepository must be initialize")
        }

        fun initial() {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository()
            }
        }

    }

}