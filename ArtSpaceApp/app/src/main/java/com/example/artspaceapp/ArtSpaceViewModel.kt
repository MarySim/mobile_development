package com.example.artspaceapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal data class Artwork(
    @param:DrawableRes val image: Int,
    @param:StringRes val title: Int,
    @param:StringRes val artist: Int,
    @param:StringRes val year: Int
)

internal data class ArtSpaceUiState(
    val artworkIndex: Int,
    val artwork: Artwork
)

internal class ArtSpaceViewModel(private val savedState: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow(artSpaceState(savedState[ARTWORK_INDEX] ?: 0))
    val uiState = _uiState.asStateFlow()

    fun previous() = moveBy(-1)

    fun next() = moveBy(1)

    private fun moveBy(offset: Int) {
        val index = (_uiState.value.artworkIndex + offset + ARTWORK_COUNT) % ARTWORK_COUNT
        savedState[ARTWORK_INDEX] = index
        _uiState.value = artSpaceState(index)
    }

    private companion object {
        const val ARTWORK_COUNT = 3
        const val ARTWORK_INDEX = "artwork_index"
    }
}

internal fun artSpaceState(index: Int = 0): ArtSpaceUiState {
    val artwork = when (index) {
        0 -> Artwork(
            R.drawable.great_wave, R.string.great_wave_title, R.string.hokusai, R.string.great_wave_year
        )
        1 -> Artwork(
            R.drawable.self_portrait, R.string.self_portrait_title, R.string.van_gogh, R.string.self_portrait_year
        )
        else -> Artwork(
            R.drawable.wheat_field, R.string.wheat_field_title, R.string.van_gogh, R.string.wheat_field_year
        )
    }
    return ArtSpaceUiState(index, artwork)
}
