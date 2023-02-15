package com.example.spojranking.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spojranking.database.DataRepo
import com.example.spojranking.database.DataRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ViModel(application: Application) : AndroidViewModel(application) {


    var users: MutableStateFlow<List<User>> = MutableStateFlow(listOf())
    private val repository: DataRepo

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    init {
        val dao = DataRoom.getInstance(application).getDao()
        repository = DataRepo(dao)

        viewModelScope.launch(Dispatchers.IO) {
            val listUsers = repository.getListData()
            if(listUsers.isEmpty()){
                for(i in getEmptyListUser()){
                    repository.insert(i)
                }
            }
            //Log.e("wwtf", "wwtf ${repository.getListData().size}")
            users.value = repository.getListData()
        }
        getAllTasks()
    }

    private fun getAllTasks() {
        users.value = getEmptyListUser()
        viewModelScope.launch(Dispatchers.IO) {
            val data = GetWebData()
            val list = data.get()
            if(list.isNotEmpty()){
                withContext(Dispatchers.Main) {
                    for (i in 0 until 26) {
                        repository.update(list[i])
                    }
                    _loadingState.value = false
                }
            }
        }
    }

    private fun getEmptyListUser(): MutableList<User> {
        val list = getListOfUser()
        for (i in 0 until 26) {
            list[i].userName = ""
            list[i].solved = 0
            list[i].target = 0
            list[i].name = ""
        }
        return list
    }
}

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ViModel::class.java)) {
            return ViModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
