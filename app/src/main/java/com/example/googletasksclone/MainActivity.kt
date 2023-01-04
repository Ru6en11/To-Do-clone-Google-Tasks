package com.example.googletasksclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googletasksclone.databinding.ActivityMainBinding
import com.example.googletasksclone.views.tasks.TasksFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, TasksFragment())
                .commit()
        }


    }

}