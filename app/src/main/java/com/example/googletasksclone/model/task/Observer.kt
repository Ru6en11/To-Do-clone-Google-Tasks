package com.example.googletasksclone.model.task

interface Observer {

    fun setChanges(tasks: List<Task>)

}