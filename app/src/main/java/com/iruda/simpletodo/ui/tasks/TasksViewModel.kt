package com.iruda.simpletodo.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iruda.simpletodo.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {

    val searchQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

    //Get list of tasks
    @ExperimentalCoroutinesApi
    val tasks = tasksFlow.asLiveData()
}