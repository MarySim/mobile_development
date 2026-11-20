package com.example.unscramble.ui

import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val words: Collection<String> = allWords,
    private val random: Random = Random.Default
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()
    private lateinit var roundWords: List<String>

    init {
        resetGame()
    }

    fun resetGame() {
        roundWords = words.shuffled(random).take(MAX_NO_OF_WORDS)
        _uiState.value = GameUiState(currentScrambledWord = scramble(roundWords.first()))
    }

    fun updateUserGuess(guess: String) {
        val state = _uiState.value
        if (state.isGameOver) return
        _uiState.value = state.copy(userGuess = guess, isGuessedWordWrong = false)
    }

    fun checkUserGuess() {
        val state = _uiState.value
        if (state.isGameOver) return
        val answer = roundWords[state.currentWordCount - 1]
        if (state.userGuess.trim().equals(answer, ignoreCase = true)) {
            advance(state, state.score + SCORE_INCREASE)
        } else {
            _uiState.value = state.copy(userGuess = "", isGuessedWordWrong = true)
        }
    }

    fun skipWord() {
        val state = _uiState.value
        if (state.isGameOver) return
        advance(state, state.score)
    }

    private fun advance(state: GameUiState, score: Int) {
        val nextState = state.copy(score = score, userGuess = "", isGuessedWordWrong = false)
        _uiState.value = if (state.currentWordCount == roundWords.size) {
            nextState.copy(isGameOver = true)
        } else {
            nextState.copy(
                currentWordCount = state.currentWordCount + 1,
                currentScrambledWord = scramble(roundWords[state.currentWordCount])
            )
        }
    }

    private fun scramble(word: String): String {
        val firstIndex = word.indices.filter { word[it] != word.first() }.random(random)
        val remainingLetters = word.filterIndexed { index, _ -> index != firstIndex }
        return word[firstIndex] + remainingLetters.toList().shuffled(random).joinToString("")
    }
}
