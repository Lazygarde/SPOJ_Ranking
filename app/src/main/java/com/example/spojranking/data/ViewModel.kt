package com.example.spojranking.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(getListOfUser())
    val uiState: StateFlow<List<User>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = GetWebData()
            val users = data.get()
            withContext(Dispatchers.Main) {
                _uiState.value = users.toMutableList()
            }
        }

    }

    private fun setSolved(solved: Int, position: Int) {
        val list = mutableListOf<User>()
        list.addAll(_uiState.value)
        list[position].setSolve(solved)
        _uiState.value = list
    }

    fun onEvent(action: ActionEvent) {
        when (action) {
            is ActionEvent.SetSolved -> setSolved(action.solved, action.position)
            else -> {}
        }
    }
}

sealed class ActionEvent {
    data class SetSolved(val solved: Int, val position: Int) : ActionEvent()
}
