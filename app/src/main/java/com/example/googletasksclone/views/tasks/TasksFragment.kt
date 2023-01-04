package com.example.googletasksclone.views.tasks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googletasksclone.Task
import com.example.googletasksclone.TasksAdapter
import com.example.googletasksclone.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private lateinit var binding: FragmentTasksBinding

    private var adapter = TasksAdapter()
    //general list
    private var tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            fetchData()
        } else {
            tasks = savedInstanceState.getParcelableArrayList<Task>("KEY") as MutableList<Task>
            adapter.tasks = tasks
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTasksBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("KEY", tasks as ArrayList<Task>)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
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

}