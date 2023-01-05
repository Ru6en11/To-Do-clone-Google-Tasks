package com.example.googletasksclone

import android.app.Application
import com.example.googletasksclone.model.task.InDatabaseTaskRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        InDatabaseTaskRepository.initial()


    }

}