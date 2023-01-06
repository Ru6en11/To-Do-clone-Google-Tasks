package com.example.googletasksclone.model.task

class InDatabaseTaskRepository : TaskRepository {

    private var tasks: MutableList<Task> =  mutableListOf()

    private val subscribers = mutableListOf<TaskSubscriber>()

    override fun getTasks(): List<Task> = tasks as List<Task>

    override fun updateTask(task: Task) {
        //todo change text to id
        tasks.forEachIndexed { ind, t ->
            if (t.text == task.text) tasks[ind] = task
        }
        notifyDataSetChanges()
    }

    override fun removeTask(task: Task) {
        tasks.remove(task)
        notifyDataSetChanges()
    }

    override fun add(task: Task) {
        tasks.add(task)
        notifyDataSetChanges()
    }

    fun addSubscriber(subscriber: TaskSubscriber) {
        subscribers.add(subscriber)
    }

    fun removeSubscriber(subscriber: TaskSubscriber) {
        subscribers.remove(subscriber)
    }

    private fun notifyDataSetChanges() {
        subscribers.forEach { it.setChanges(tasks) }
    }

    companion object {

        private var INSTANCE: InDatabaseTaskRepository? = null

        fun get(): InDatabaseTaskRepository {
            return INSTANCE ?: throw IllegalStateException("InDatabaseTaskRepository must be initialize")
        }

        fun initial() {
            if (INSTANCE == null) {
                INSTANCE = InDatabaseTaskRepository()
            }
        }

    }

}