package com.example.unscramble.ui

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val words = listOf(
        "planet", "winter", "flower", "library", "ocean",
        "candle", "market", "garden", "squirrel", "journey"
    )
    private val game = GameViewModel(words, Random(6))
    private val answers = words.associateBy(::sortedLetters)

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        game.updateUserGuess("wrong")
        game.checkUserGuess()

        val answer = currentAnswer()
        game.updateUserGuess(" ${answer.uppercase()} ")
        game.checkUserGuess()

        val state = game.uiState.value
        assertEquals(SCORE_INCREASE, state.score)
        assertEquals(2, state.currentWordCount)
        assertFalse(state.isGuessedWordWrong)
        assertEquals("", state.userGuess)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val initialWord = game.uiState.value.currentScrambledWord
        game.updateUserGuess("wrong")
        game.checkUserGuess()

        val state = game.uiState.value
        assertTrue(state.isGuessedWordWrong)
        assertEquals(0, state.score)
        assertEquals(1, state.currentWordCount)
        assertEquals(initialWord, state.currentScrambledWord)
        assertEquals("", state.userGuess)
    }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val state = GameViewModel().uiState.value

        assertTrue(allWords.any {
            it != state.currentScrambledWord && sortedLetters(it) == sortedLetters(state.currentScrambledWord)
        })
        assertEquals(1, state.currentWordCount)
        assertEquals(0, state.score)
        assertFalse(state.isGameOver)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        val seen = mutableSetOf<String>()
        repeat(MAX_NO_OF_WORDS) { index ->
            val answer = currentAnswer()
            assertTrue(seen.add(answer))
            assertNotEquals(answer, game.uiState.value.currentScrambledWord)
            game.updateUserGuess(answer)
            game.checkUserGuess()
            assertEquals((index + 1) * SCORE_INCREASE, game.uiState.value.score)
        }
        val finalState = game.uiState.value
        assertTrue(finalState.isGameOver)
        assertEquals(MAX_NO_OF_WORDS, finalState.currentWordCount)

        game.updateUserGuess("extra")
        game.checkUserGuess()
        game.skipWord()
        assertEquals(finalState, game.uiState.value)

        game.resetGame()
        val newGame = game.uiState.value
        assertFalse(newGame.isGameOver)
        assertEquals(0, newGame.score)
        assertEquals(1, newGame.currentWordCount)
        assertTrue(answers.containsKey(sortedLetters(newGame.currentScrambledWord)))
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        game.updateUserGuess(currentAnswer())
        game.checkUserGuess()
        val previousState = game.uiState.value

        game.updateUserGuess("wrong")
        game.checkUserGuess()
        game.skipWord()
        val state = game.uiState.value

        assertEquals(previousState.score, state.score)
        assertEquals(previousState.currentWordCount + 1, state.currentWordCount)
        assertNotEquals(previousState.currentScrambledWord, state.currentScrambledWord)
        assertFalse(state.isGuessedWordWrong)
        assertEquals("", state.userGuess)
    }

    private fun currentAnswer() =
        answers.getValue(sortedLetters(game.uiState.value.currentScrambledWord))

    private fun sortedLetters(word: String) = word.toCharArray().sorted().joinToString("")
}
