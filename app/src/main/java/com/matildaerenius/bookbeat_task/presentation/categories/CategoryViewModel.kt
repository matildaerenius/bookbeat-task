package com.matildaerenius.bookbeat_task.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matildaerenius.bookbeat_task.data.repository.BookBeatRepository
import com.matildaerenius.bookbeat_task.domain.model.Category
import com.matildaerenius.bookbeat_task.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
            } catch (e: IOException) {
                Log.e("CategoryViewModel", "Krasch vid hämtning av kategori", e)
                _uiState.value =
                    UiState.Error("Inget internet. Kolla ditt nätverk och försök igen.")
            } catch (e: HttpException) {
                Log.e("CategoryViewModel", "Krasch vid hämtning av kategori", e)
                _uiState.value = UiState.Error("Ett fel uppstod på servern. Försök igen senare.")
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Krasch vid hämtning av kategori", e)
                _uiState.value = UiState.Error("Ett oväntat fel uppstod. Försök igen.")
            }
        }
    }
}