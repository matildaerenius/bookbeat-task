package com.matildaerenius.bookbeat_task.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matildaerenius.bookbeat_task.data.repository.BookBeatRepository
import com.matildaerenius.bookbeat_task.domain.model.Category
import com.matildaerenius.bookbeat_task.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val repository = BookBeatRepository()

    private val _uiState = MutableStateFlow<UiState<List<Category>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Category>>> = _uiState.asStateFlow()

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val categories = repository.getCategories()
                _uiState.value = UiState.Success(categories)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Ett oväntat fel uppstod")
            }
        }
    }
}