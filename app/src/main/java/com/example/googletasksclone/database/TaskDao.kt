package com.example.googletasksclone.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.googletasksclone.model.task.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getTask(id: UUID): Task?

    @Insert
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}