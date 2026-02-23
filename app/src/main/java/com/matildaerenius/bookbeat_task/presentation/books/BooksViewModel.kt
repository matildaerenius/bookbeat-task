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
import retrofit2.HttpException
import java.io.IOException

class BooksViewModel : ViewModel() {

    private val repository = BookBeatRepository()

    private val _uiState = MutableStateFlow<UiState<List<Book>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Book>>> = _uiState.asStateFlow()
    private val allBooks = mutableListOf<Book>()
    private var nextUrl: String? = null

    fun fetchBooks(url: String) {
        nextUrl = url
        allBooks.clear()
        loadData()
    }

    fun loadMoreBooks() {
        if (nextUrl != null) {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            if (allBooks.isEmpty()) {
                _uiState.value = UiState.Loading
            }

            try {

                val bookPage = repository.getBooks(nextUrl!!)

                allBooks.addAll(bookPage.books)
                nextUrl = bookPage.nextUrl

                _uiState.value = UiState.Success(allBooks.toList())

            } catch (e: IOException) {
                if (allBooks.isEmpty()) {
                    _uiState.value =
                        UiState.Error("Inget internet. Kolla ditt nätverk och försök igen.")
                }
            } catch (e: HttpException) {
                if (allBooks.isEmpty()) {
                    _uiState.value =
                        UiState.Error("Ett fel uppstod på servern. Försök igen senare.")
                }
            } catch (e: Exception) {
                if (allBooks.isEmpty()) {
                    _uiState.value = UiState.Error("Ett oväntat fel uppstod. Försök igen.")
                }
            }
        }
    }
}
