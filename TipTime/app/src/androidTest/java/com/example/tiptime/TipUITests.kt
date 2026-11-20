package com.example.tiptime

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TipUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText(context.getString(R.string.bill_amount))
            .performTextInput("10")
        composeTestRule.onNodeWithText(context.getString(R.string.tip_percentage))
            .performTextInput("20")

        val formattedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText(context.getString(R.string.tip_amount, formattedTip))
            .assertIsDisplayed()
    }
}
