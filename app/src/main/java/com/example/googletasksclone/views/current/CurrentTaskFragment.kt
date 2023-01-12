package com.example.googletasksclone.views.current

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.googletasksclone.R
import com.example.googletasksclone.base.navigator.Navigator
import com.example.googletasksclone.databinding.FragmentCurrentTaskBinding
import com.example.googletasksclone.model.task.Task

class CurrentTaskFragment : Fragment() {

    private lateinit var binding: FragmentCurrentTaskBinding
    private val viewModel: CurrentTaskViewModel by activityViewModels()
    private var navigator: Navigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

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

        val task = requireArguments().getParcelable<Task>(ARGS_KEY) as Task
        if (savedInstanceState == null) viewModel.initState(task)

        viewModel.task.observe(viewLifecycleOwner) {
            renderState(it)
        }

        binding.goBackImageButton.setOnClickListener {
            navigator?.goBack()
        }

        binding.favouriteImageButton.setOnClickListener {
            viewModel.updateTask(
                task = viewModel.task.value!!.copy(
                    isFavourite = !viewModel.task.value!!.isFavourite
                )
            )
        }

        binding.addInCompletedButton.setOnClickListener {
            viewModel.updateTask(
                task = viewModel.task.value!!.copy(
                    isCompleted = !viewModel.task.value!!.isCompleted
                )
            )
            if (viewModel.task.value?.isCompleted!!) navigator?.goBack()
        }

        binding.deleteImageButton.setOnClickListener {
            viewModel.deleteTask()
            navigator?.goBack()
        }

    }

    override fun onStop() {
        super.onStop()

        viewModel.updateTask(
            task = viewModel.task.value!!.copy(
                text = binding.taskTitleEditText.text.toString(),
                additionalInfo = binding.additInfoEditText.text.toString()
            )
        )
    }

    private fun renderState(task: Task) = binding.run {
        taskTitleEditText.setText(task.text)
        additInfoEditText.setText(task.additionalInfo)
        favouriteImageButton.setImageResource(
            if (task.isFavourite) R.drawable.ic_star else R.drawable.ic_star_border
        )
        addInCompletedButton.setText(
            if (task.isCompleted) R.string.uncompleted else R.string.completed
        )
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