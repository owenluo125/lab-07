package com.example.lab7

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.AnnotatedString
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DriversScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigate_to_drivers_add_clear_and_return_home() {
        // verify that the app starts on home
        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertIsDisplayed()

        // navigate to drivers
        composeTestRule
            .onNodeWithTag("driversButton")
            .performClick()

        // verify the drivers screen
        composeTestRule
            .onNodeWithText("FormulaZero Drivers")
            .assertIsDisplayed()

        // enter the first driver's name
        composeTestRule
            .onNodeWithTag("driverInput")
            .performTextInput("Lewis Hamilton")

        // add the first driver
        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        // verify the first driver appears
        composeTestRule
            .onNodeWithText("Lewis Hamilton")
            .assertIsDisplayed()

        // verify input is cleared
        composeTestRule
            .onNodeWithTag("driverInput")
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.EditableText,
                    AnnotatedString("")
                )
            )

        // enter second name
        composeTestRule
            .onNodeWithTag("driverInput")
            .performTextInput("Max Verstappen")

        // add the second driver
        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        // verify the second driver appears
        composeTestRule
            .onNodeWithText("Max Verstappen")
            .assertIsDisplayed()

        // clear all drivers
        composeTestRule
            .onNodeWithTag("clearDriversButton")
            .performClick()

        // verify the drivers dont appear
        composeTestRule
            .onNodeWithText("Lewis Hamilton")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("Max Verstappen")
            .assertDoesNotExist()

        // return to home
        sscomposeTestRule
            .onNodeWithTag("driversBackButton")
            .performClick()

        // verify the home screen
        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertIsDisplayed()
    }
}