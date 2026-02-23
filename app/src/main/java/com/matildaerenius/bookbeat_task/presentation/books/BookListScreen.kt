package com.matildaerenius.bookbeat_task.presentation.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matildaerenius.bookbeat_task.domain.model.Book
import com.matildaerenius.bookbeat_task.presentation.components.BookCard
import com.matildaerenius.bookbeat_task.presentation.components.ErrorStateView
import com.matildaerenius.bookbeat_task.presentation.state.UiState

@Composable
fun BookListScreen(
    viewModel: BooksViewModel,
    booksUrl: String,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(booksUrl) {
        viewModel.fetchBooks(booksUrl)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            is UiState.Error -> {
                ErrorStateView(errorMessage = state.message)
            }

            is UiState.Success -> {
                BookListContent(
                    books = state.data,
                    onLoadMoreClick = { viewModel.loadMoreBooks() }
                )
            }
        }
    }
}

@Composable
private fun BookListContent(
    books: List<Book>,
    onLoadMoreClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(books) { book ->
            BookCard(book = book)
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onLoadMoreClick) {
                    Text("Visa fler böcker")
                }
            }
        }
    }
}