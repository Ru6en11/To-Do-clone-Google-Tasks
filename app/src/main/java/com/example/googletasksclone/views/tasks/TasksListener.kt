package com.example.googletasksclone.views.tasks

import com.example.googletasksclone.model.task.Task

interface TasksListener {



    fun onClickTask(task: Task)

    fun showTaskScreen(task:Task)


}