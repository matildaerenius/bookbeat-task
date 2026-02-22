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
    private val allBooks = mutableListOf<Book>()
    private var currentPage = 1
    private var currentCategoryUrl = ""

    fun fetchBooks(url: String) {
        currentCategoryUrl = url
        currentPage = 1
        allBooks.clear()
        loadData()
    }
    fun loadMoreBooks() {
        currentPage += 1
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            if (allBooks.isEmpty()) {
                _uiState.value = UiState.Loading
            }

            try {
                val separator = if (currentCategoryUrl.contains("?")) "&" else "?"
                val paginatedUrl = "${currentCategoryUrl}${separator}page=$currentPage"

                val newBooks = repository.getBooks(paginatedUrl)

                allBooks.addAll(newBooks)

                _uiState.value = UiState.Success(allBooks.toList())

            } catch (e: Exception) {
                if (allBooks.isEmpty()) {
                    _uiState.value = UiState.Error(e.message ?: "Kunde inte hämta böcker")
                }
            }
        }
    }
}
