package com.example.googletasksclone.views.tasks


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googletasksclone.R
import com.example.googletasksclone.base.navigator.Navigator
import com.example.googletasksclone.databinding.CreateTaskBottomSheetBinding
import com.example.googletasksclone.model.task.Task
import com.example.googletasksclone.databinding.FragmentTasksBinding
import com.example.googletasksclone.views.category.CategoryViewPager2Adapter
import com.example.googletasksclone.views.current.CurrentTaskFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

val categories = arrayOf(
    "Избранные",
    "Мои задачи"
)

class TasksFragment : Fragment()  {

    private lateinit var binding: FragmentTasksBinding
    private val viewModel: TasksViewModel by activityViewModels()
    private var navigator: Navigator? = null
    private lateinit var adapter: CategoryViewPager2Adapter
    private lateinit var newTaskDialog: BottomSheetDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.initState()
        } else {
            @Suppress("DEPRECATION")
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

        adapter = CategoryViewPager2Adapter(this)
        binding.categoryViewPager2.adapter = adapter

        TabLayoutMediator(binding.categoryTabLayout, binding.categoryViewPager2) {tab, position ->
            tab.text = categories[position]
        }.attach()

        newTaskDialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
        newTaskDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        createTaskDialog()

        binding.createItemFab.setOnClickListener {
            newTaskDialog.show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            KEY_STATE,
            viewModel.tasks.value as ArrayList<Task>
        )
    }

    private fun createTaskDialog() {

        val dialogBinding = CreateTaskBottomSheetBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.saveTaskButton.isEnabled = false

        dialogBinding.additionalInfoImageBtn.setOnClickListener {
            dialogBinding.additionalInfoEditText.visibility = View.VISIBLE
        }
        dialogBinding.addInFavouriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            dialogBinding.addInFavouriteCheckBox.setButtonDrawable(
                    if (isChecked) R.drawable.ic_star else R.drawable.ic_star_border
                )
        }

        dialogBinding.taskTitleEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                dialogBinding.saveTaskButton.isEnabled = s?.isNotBlank() == true
            }
        })

        dialogBinding.saveTaskButton.setOnClickListener {
            val task = Task(
                isCompleted = false,
                text = dialogBinding.taskTitleEditText.text.toString(),
                additionalInfo = dialogBinding.additionalInfoEditText.text.toString(),
                isFavourite = dialogBinding.addInFavouriteCheckBox.isChecked
            )
            viewModel.createTask(task)
            newTaskDialog.dismiss()
        }
        dialogBinding.taskTitleEditText.requestFocus()

        newTaskDialog.setOnDismissListener {
            dialogBinding.apply {
                taskTitleEditText.text.clear()
                additionalInfoEditText.text.clear()
                additionalInfoEditText.visibility = View.GONE
                addInFavouriteCheckBox.isChecked = false
            }
        }

        newTaskDialog.setContentView(dialogBinding.root)

    }

    companion object {

        const val EVENT_DELETE_TASK = "com.example.googletasksclone.views.tasks.delete_task"
        const val KEY_REMOVED_TASK = "com.example.googletasksclone.views.tasks.delete_task"
        private const val KEY_STATE = "com.example.googletasksclone.views.tasks.key_state"

        fun newInstance(): TasksFragment = TasksFragment()

    }

}