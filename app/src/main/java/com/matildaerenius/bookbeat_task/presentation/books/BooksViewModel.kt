package com.matildaerenius.bookbeat_task.presentation.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matildaerenius.bookbeat_task.data.repository.BookBeatRepository
import com.matildaerenius.bookbeat_task.domain.model.Book
import com.matildaerenius.bookbeat_task.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val repository = BookBeatRepository()

    private val _uiState = MutableStateFlow<UiState<List<Book>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Book>>> = _uiState.asStateFlow()

    fun fetchBooks(url: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val books = repository.getBooks(url)
                _uiState.value = UiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Kunde inte hämta böcker")
            }
        }
    }
}