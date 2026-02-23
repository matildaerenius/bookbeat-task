package com.matildaerenius.bookbeat_task.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matildaerenius.bookbeat_task.presentation.components.CategoryCard
import com.matildaerenius.bookbeat_task.presentation.components.ErrorStateView
import com.matildaerenius.bookbeat_task.presentation.state.UiState

@Composable
fun CategoryListScreen(
    viewModel: CategoryViewModel,
    onCategoryClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UiState.Error -> {
                ErrorStateView(errorMessage = state.message)
            }

            is UiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.data) { category ->
                        CategoryCard(
                            category = category,
                            onCategoryClick = {
                                category.booksUrl?.let { url -> onCategoryClick(url) }
                            }
                        )
                    }
                }
            }
        }
    }
}