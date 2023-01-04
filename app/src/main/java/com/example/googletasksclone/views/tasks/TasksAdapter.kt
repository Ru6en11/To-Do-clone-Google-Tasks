package com.example.googletasksclone.views.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googletasksclone.R
import com.example.googletasksclone.model.task.Task
import com.example.googletasksclone.databinding.TaskItemBinding

class TasksAdapter(private val listener: TasksListener) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    var tasks: List<Task> = mutableListOf()
            set(newValue) {
                if (newValue != field) {
                    field = newValue
                    notifyDataSetChanged()
                }
            }

    inner class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TaskItemBinding.bind(view)

        fun bind(task: Task) = binding.run {

            isCompletedCheckBox.isChecked = task.isCompleted
            isCompletedCheckBox.text = task.text

            isCompletedCheckBox.setOnClickListener {
                task.isCompleted = isCompletedCheckBox.isChecked
                listener.updateTask(task)
                notifyDataSetChanged()
            }

            val imageRes = if (task.isFavourite) R.drawable.ic_star else R.drawable.ic_star_border
            isFavouriteImageButton.setImageResource(imageRes)

            isFavouriteImageButton.setOnClickListener {
                task.isFavourite = !task.isFavourite
                listener.updateTask(task)
                val imRes = if (task.isFavourite) R.drawable.ic_star else R.drawable.ic_star_border
                isFavouriteImageButton.setImageResource(imRes)
                notifyDataSetChanged()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TasksViewHolder(view)

    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

}