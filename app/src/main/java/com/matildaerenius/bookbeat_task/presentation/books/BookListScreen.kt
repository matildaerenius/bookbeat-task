package com.matildaerenius.bookbeat_task.presentation.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.matildaerenius.bookbeat_task.presentation.components.BookCard
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
                is UiState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                is UiState.Error -> Text("Fel: ${state.message}", Modifier.align(Alignment.Center))
                is UiState.Success -> {
                    LazyColumn {
                        items(state.data) { book ->
                            BookCard(book = book)
                        }
                    }
                }
            }
        }
    }