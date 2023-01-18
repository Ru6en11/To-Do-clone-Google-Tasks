package com.example.googletasksclone.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.googletasksclone.model.task.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE isCompleted = 0")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE isFavourite = 1 AND isCompleted = 0")
    fun getFavouriteTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE isCompleted = 1")
    fun getCompletedTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getTask(id: UUID): LiveData<Task?>

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}