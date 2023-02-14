package com.example.spojranking.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(getEmptyListUser())
    val uiState: StateFlow<List<User>> = _uiState.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    init {
        _uiState.value = getEmptyListUser()
        viewModelScope.launch(Dispatchers.IO) {
            val data = GetWebData()
            val users = data.get()
            if (users.isEmpty()) {
                _uiState.value = getEmptyListUser()
            } else {
                withContext(Dispatchers.Main) {
                    _uiState.value = users
                    _loadingState.value = false
                }
            }
        }

    }
}
fun getEmptyListUser( ): List<User> {
    val list = getListOfUser( )
    for (i in 0 until 26) {
        list[i].userName = ""
        list[i].solved = 0
        list[i].target = 0
        list[i].name = ""
    }
    return list
}
