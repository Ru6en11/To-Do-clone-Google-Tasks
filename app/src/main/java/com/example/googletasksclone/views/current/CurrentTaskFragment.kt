package com.example.googletasksclone.views.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.googletasksclone.databinding.FragmentCurrentTaskBinding
import com.example.googletasksclone.model.task.Task

class CurrentTaskFragment : Fragment() {

    private lateinit var binding: FragmentCurrentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {

        private const val ARGS_KEY = "om.example.googletasksclone.views.current.args_key"

        fun newInstance(task: Task): CurrentTaskFragment {
            val args = Bundle().apply {
                putParcelable(ARGS_KEY, task)
            }

            val fragment = CurrentTaskFragment()
            fragment.arguments = args

            return fragment
        }

    }

}