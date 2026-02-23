package com.matildaerenius.bookbeat_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.matildaerenius.bookbeat_task.presentation.books.BookListScreen
import com.matildaerenius.bookbeat_task.presentation.books.BooksViewModel
import com.matildaerenius.bookbeat_task.presentation.categories.CategoryListScreen
import com.matildaerenius.bookbeat_task.presentation.categories.CategoryViewModel
import com.matildaerenius.bookbeat_task.presentation.navigation.BookListRoute
import com.matildaerenius.bookbeat_task.presentation.navigation.CategoryListRoute
import com.matildaerenius.bookbeat_task.presentation.theme.BookbeattaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookbeattaskTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = CategoryListRoute
                ) {
                    composable<CategoryListRoute> {
                        val categoryViewModel: CategoryViewModel = viewModel()
                        CategoryListScreen(
                            viewModel = categoryViewModel,
                            onCategoryClick = { url ->
                                navController.navigate(BookListRoute(url = url))
                            }
                        )
                    }

                    composable<BookListRoute> { backStackEntry ->
                        val route = backStackEntry.toRoute<BookListRoute>()
                        val booksViewModel: BooksViewModel = viewModel()

                        BookListScreen(
                            viewModel = booksViewModel,
                            booksUrl = route.url,
                        )
                    }
                }
            }
        }
    }
}
