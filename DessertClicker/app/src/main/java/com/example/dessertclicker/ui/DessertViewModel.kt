package com.example.dessertclicker.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal data class DessertUiState(
    val dessertsSold: Int,
    val revenue: Int,
    val currentDessert: Dessert
)

internal class DessertViewModel(private val savedState: SavedStateHandle) : ViewModel() {
    private val sales = savedState.get<IntArray>(SALES)
    private val _uiState = MutableStateFlow(dessertState(sales?.get(0) ?: 0, sales?.get(1) ?: 0))
    val uiState = _uiState.asStateFlow()

    fun sellDessert() {
        val previous = _uiState.value
        val sold = previous.dessertsSold + 1
        val revenue = previous.revenue + previous.currentDessert.price
        savedState[SALES] = intArrayOf(sold, revenue)
        _uiState.value = dessertState(sold, revenue)
    }

    private companion object {
        const val SALES = "sales"
    }
}

internal fun dessertState(sold: Int = 0, revenue: Int = 0): DessertUiState {
    val dessert = Datasource.dessertList.last { sold >= it.startProductionAmount }
    return DessertUiState(sold, revenue, dessert)
}
