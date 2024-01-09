package com.example.personalprofile.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import com.example.personalprofile.R
import com.example.personalprofile.ui.theme.PastalBlue
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class ApplicationComponentsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun commonTextViewTest() {
        composeTestRule.setContent {
            CommonTextView(text = "Common text Test")
        }

        composeTestRule.onNodeWithText("Common text Test").assertIsDisplayed()
    }

    @Test
    fun commonTitleTest() {
        composeTestRule.setContent {
            CommonTitle(logo = R.drawable.about_me, title = "About Me", modifier = Modifier)
        }

        composeTestRule.onNodeWithContentDescription("image").assertIsDisplayed()
        composeTestRule.onNodeWithText("About Me").assertIsDisplayed()
    }

    @Test
    fun contentSectionItemTest() {
        composeTestRule.setContent {
            ContentSectionItem(title = "Android test") {
                CommonTextView(text = "Android test description is printed")
            }
        }

        composeTestRule.onNodeWithText("Android test: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Android test description is printed").assertIsDisplayed()
    }

    @Test
    fun sectionItemTest() {
        composeTestRule.setContent {
            SectionItem(logo = R.drawable.skills, title = "Key Skills") {
                CommonTextView(
                    text = "section item test",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .background(color = PastalBlue)
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("image").assertIsDisplayed()
        composeTestRule.onNodeWithText("Key Skills").assertIsDisplayed()
        composeTestRule.onNodeWithText("section item test").assertIsDisplayed()
    }

    @Test
    fun navigationDrawerHeaderTest() {
        composeTestRule.setContent {
            NavigationDrawerHeader()
        }

        composeTestRule.onNodeWithContentDescription("Personal image").assertIsDisplayed()
    }

    @Test
    fun navigationDrawerItemTest() {
        composeTestRule.setContent {
            NavigationDrawerItem(logo = R.drawable.call_navigation_item, text = "099")
        }

        composeTestRule.onNodeWithText("099").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("navigation image").assertIsDisplayed()
    }

    @Test
    fun resumeSectionTest() {
        composeTestRule.setContent {
            ResumeSection()
        }

        composeTestRule.onNodeWithText("Scan the QR code to access the resume").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("QR code to view Resume").assertIsDisplayed()
        Intents.init()
        composeTestRule.onNodeWithText("Click to view/download Resume").assertIsDisplayed()
            .assertHasClickAction().performClick()
        Intents.intended(
            Matchers.allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://drive.google.com/file/d/1-jEXZwU4C9rd17-tYmEmg33_5221WmZT/view?usp=sharing")
            )
        )
        Intents.release()
    }

    @Test
    fun experienceItemViewTest() {
        composeTestRule.setContent {
            ExperienceItemView()
        }

        composeTestRule.onNodeWithText("Company name: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Project name: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tec stack: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Job role: ").assertIsDisplayed()
    }

    @Test
    fun educationItemViewTest() {
        composeTestRule.setContent {
            EducationItemView()
        }

        composeTestRule.onNodeWithText("Institute name: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Degree: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Branch: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Marks %: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Duration: ").assertIsDisplayed()
    }

    @Test
    fun aboutMeTest() {
        composeTestRule.setContent {
            AboutMe()
        }

        composeTestRule.onNodeWithContentDescription("My Image").assertIsDisplayed()
        composeTestRule.onNodeWithText("Adaptable developer with more than ONE year and FIVE months of experience in software engineering, dedicated to implementing cutting edge technologies to optimize development efficiency. Proficient in swiftly acquiring and applying intricate technological concepts.")
    }

    @Test
    fun projectItemViewTest() {
        composeTestRule.setContent {
            ProjectItemView()
        }

        composeTestRule.onAllNodesWithText("References: ").assertCountEquals(2)
        Intents.init()
        composeTestRule.onNodeWithTag("git0").assertIsDisplayed().assertHasClickAction()
            .performClick()
        Intents.intended(
            Matchers.allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://github.com/SampathVedantham1/NotePad/tree/first-version")
            )
        )
        Intents.release()
    }

    @Test
    fun navigationDrawerVisibilityTest() {
        composeTestRule.setContent {
            HomeScreen()
        }

        composeTestRule.onNodeWithContentDescription("More menu").assertIsDisplayed()
            .assertHasClickAction().performClick()
        composeTestRule.onNodeWithContentDescription("Personal image").assertIsDisplayed()
        composeTestRule.onNodeWithText("Scan the QR code to access the resume").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("QR code to view Resume").assertIsDisplayed()
        composeTestRule.onNodeWithText("Click to view/download Resume").assertIsDisplayed()
            .assertHasClickAction()
        composeTestRule.onNodeWithText("9666150281").assertIsDisplayed()
        composeTestRule.onNodeWithText("sampathvedantham@gmail.com").assertIsDisplayed()
    }
}