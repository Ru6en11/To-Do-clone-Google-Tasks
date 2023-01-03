package com.example.googletasksclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googletasksclone.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), TasksListener {

    lateinit var binding: ActivityMainBinding

    private var adapter = TasksAdapter(this)
    //general list
    private var tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        if (savedInstanceState == null) {
            fetchData()
        } else {
            tasks = savedInstanceState.getParcelableArrayList<Task>("KEY") as MutableList<Task>
            adapter.tasks = tasks
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("KEY", tasks as ArrayList<Task>)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun fetchData() {
        tasks.add(Task(true, "Task 0", true))
        for (i in 1..10) {
            tasks.add(
                Task(false, "Task $i", false)
            )
        }
        adapter.tasks = tasks
    }

    override fun updateTask(task: Task) {
        //todo change text to id
        tasks.forEachIndexed { ind, t ->
            if (t.text == task.text) tasks[ind] = task
        }
    }

    override fun removeTask(task: Task) {
        tasks.remove(task)
    }

}