package com.example.googletasksclone.model.task

interface TaskSubscriber {

    fun setChanges(tasks: List<Task>)

}