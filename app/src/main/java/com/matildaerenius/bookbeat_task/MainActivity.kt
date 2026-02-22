package com.matildaerenius.bookbeat_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.matildaerenius.bookbeat_task.presentation.books.BookListScreen
import com.matildaerenius.bookbeat_task.presentation.books.BooksViewModel
import com.matildaerenius.bookbeat_task.presentation.categories.CategoryListScreen
import com.matildaerenius.bookbeat_task.presentation.categories.CategoryViewModel
import com.matildaerenius.bookbeat_task.presentation.navigation.Routes
import com.matildaerenius.bookbeat_task.presentation.theme.BookbeattaskTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookbeattaskTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.CATEGORIES
                ) {
                    composable(Routes.CATEGORIES) {
                        val categoryViewModel: CategoryViewModel = viewModel()
                        CategoryListScreen(
                            viewModel = categoryViewModel,
                            onCategoryClick = { url ->
                                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                                navController.navigate("books/$encodedUrl")
                            }
                        )
                    }

                    composable(
                        route = Routes.BOOKS,
                        arguments = listOf(navArgument("url") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val booksUrl = backStackEntry.arguments?.getString("url") ?: ""
                        val booksViewModel: BooksViewModel = viewModel()

                        BookListScreen(
                            viewModel = booksViewModel,
                            booksUrl = booksUrl,
                        )
                    }
                }
            }
        }
    }
}
