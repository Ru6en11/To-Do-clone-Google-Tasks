package com.example.googletasksclone.views.tasks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googletasksclone.Task
import com.example.googletasksclone.TasksAdapter
import com.example.googletasksclone.TasksListener
import com.example.googletasksclone.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private lateinit var binding: FragmentTasksBinding
    private val viewModel: TasksViewModel by activityViewModels()
    private lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.initState()
        } else {
            val state = savedInstanceState.getParcelableArrayList<Task>(KEY_STATE) as MutableList<Task>
            viewModel.initState(state)
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

        adapter = TasksAdapter(viewModel as TasksListener)

        initRecyclerView()

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.tasks = it
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            KEY_STATE,
            viewModel.tasks.value as ArrayList<Task>
        )
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    companion object {

        private const val KEY_STATE = "com.example.googletasksclone.views.tasks.key_state"

        fun newInstance(): TasksFragment = TasksFragment()

    }

}