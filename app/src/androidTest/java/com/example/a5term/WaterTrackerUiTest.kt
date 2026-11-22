package com.example.a5term

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.a5term.ui.theme._5termTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WaterTrackerUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val addText = "+250 мл"
    private val finishText = "Завершить день"

    private fun streakText(days: Int) = "Дней подряд с нормой 1500 мл: $days"

    @Before
    fun setUp() {
        composeTestRule.setContent {
            _5termTheme() {
                WaterTracker()
            }
        }
    }

    private fun addWater(times: Int) {
        repeat(times) {
            composeTestRule.onNodeWithText(addText).performClick()
        }
    }

    // ---------- 1) Кнопка добавления жидкости ----------

    @Test
    fun addButton_exists_and_enabled() {
        composeTestRule.onNodeWithText(addText).assertIsDisplayed().assertIsEnabled()
    }

    @Test
    fun addButton_initialValue_isZero() {
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    @Test
    fun addButton_click_adds250() {
        composeTestRule.onNodeWithText(addText).performClick()
        composeTestRule.onNodeWithText("250 мл").assertIsDisplayed()
    }

    @Test
    fun addButton_multipleClicks_accumulate() {
        addWater(3)
        composeTestRule.onNodeWithText("750 мл").assertIsDisplayed()
    }

    // ---------- 2) Кнопка «Завершить день» ----------

    @Test
    fun finishButton_exists_and_enabled() {
        composeTestRule.onNodeWithText(finishText).assertIsDisplayed().assertIsEnabled()
    }

    @Test
    fun finishButton_resetsWaterCounter() {
        addWater(2)
        composeTestRule.onNodeWithText("500 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText(finishText).performClick()

        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    // ---------- 3) Текст с количеством дней подряд ----------

    @Test
    fun streakText_initially_zero() {
        composeTestRule.onNodeWithText(streakText(0)).assertIsDisplayed()
    }

    @Test
    fun streakText_increases_whenGoalReached() {
        addWater(6) // 6 * 250 = 1500 мл
        composeTestRule.onNodeWithText("1500 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText(finishText).performClick()

        composeTestRule.onNodeWithText(streakText(1)).assertIsDisplayed()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    @Test
    fun streakText_increases_whenMoreThanGoal() {
        addWater(7) // 1750 мл
        composeTestRule.onNodeWithText(finishText).performClick()

        composeTestRule.onNodeWithText(streakText(1)).assertIsDisplayed()
    }

    @Test
    fun streakText_accumulates_overSeveralDays() {
        repeat(3) {
            addWater(6)
            composeTestRule.onNodeWithText(finishText).performClick()
        }
        composeTestRule.onNodeWithText(streakText(3)).assertIsDisplayed()
    }

    @Test
    fun streakText_notIncreased_whenBelowGoal() {
        addWater(5) // 1250 мл < 1500
        composeTestRule.onNodeWithText(finishText).performClick()

        composeTestRule.onNodeWithText(streakText(0)).assertIsDisplayed()
    }

    @Test
    fun streakText_resetsToZero_afterBadDay() {
        // два удачных дня
        repeat(2) {
            addWater(6)
            composeTestRule.onNodeWithText(finishText).performClick()
        }
        composeTestRule.onNodeWithText(streakText(2)).assertIsDisplayed()

        // плохой день — серия обнуляется
        addWater(2)
        composeTestRule.onNodeWithText(finishText).performClick()

        composeTestRule.onNodeWithText(streakText(0)).assertIsDisplayed()
    }
}
