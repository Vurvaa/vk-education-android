package com.vurvaa

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import com.vurvaa.data.AppCatalog
import com.vurvaa.ui.navigation.AppNavHost
import com.vurvaa.ui.theme.FirstAppTheme
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], qualifiers = "w400dp-h900dp")
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class AppNavigationTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun eachCatalogItemOpensItsOwnCardAndBackReturnsToList() {
        launchApp()
        saveScreenshot("catalog")
        AppCatalog.apps.forEach { app ->
            compose.onNodeWithTag("apps_list")
                .performScrollToNode(hasTestTag("catalog_app_${app.id}"))
            compose.onNodeWithTag("catalog_app_${app.id}").performClick()
            compose.onNodeWithTag("app_details").assertIsDisplayed()
            compose.onNodeWithTag("app_details_name").assertTextEquals(app.name)
            if (app == AppCatalog.apps.first()) saveScreenshot("details")
            compose.onNodeWithContentDescription("Назад").performClick()
            compose.onNodeWithTag("apps_list").assertIsDisplayed()
            compose.onNodeWithTag("catalog_app_${app.id}").assertIsDisplayed()
        }
    }

    @Test
    fun showcasePagesContainThreeClickableAppsAndStopAtPageThree() {
        launchApp()
        compose.onNodeWithTag("catalog_app_${AppCatalog.apps.first().id}").performClick()
        compose.onNodeWithTag("showcase_pager").performScrollTo()
        AppCatalog.showcasePages.forEachIndexed { page, apps ->
            assertCurrentPage(page)
            apps.forEach { app ->
                compose.onNodeWithTag("showcase_app_${app.id}").assertIsDisplayed()
            }
            compose.onAllNodes(
                hasAnyAncestor(hasTestTag("showcase_pager")) and hasClickAction(),
            ).assertCountEquals(3)
            saveScreenshot("showcase-page-${page + 1}")
            compose.onNodeWithTag("showcase_pager").performTouchInput { swipeLeft() }
        }
        assertCurrentPage(2)
    }

    @Test
    fun everyShowcaseAppReturnsToTheExistingCatalog() {
        launchApp()
        AppCatalog.showcasePages.forEachIndexed { page, apps ->
            apps.forEach { app ->
                compose.onNodeWithTag("apps_list").performScrollToNode(
                    hasTestTag("catalog_app_${AppCatalog.apps.first().id}"),
                )
                compose.onNodeWithTag("catalog_app_${AppCatalog.apps.first().id}").performClick()
                compose.onNodeWithTag("showcase_pager").performScrollTo()
                repeat(page) {
                    compose.onNodeWithTag("showcase_pager").performTouchInput { swipeLeft() }
                    compose.waitForIdle()
                }
                compose.onNodeWithTag("showcase_app_${app.id}").performClick()
                compose.onNodeWithTag("apps_list").assertIsDisplayed()
            }
        }
    }

    private fun launchApp() {
        compose.setContent {
            FirstAppTheme(dynamicColor = false) {
                AppNavHost()
            }
        }
    }

    private fun assertCurrentPage(page: Int) {
        compose.onNodeWithTag("showcase_pager").assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.StateDescription,
                "Страница ${page + 1} из 3",
            ),
        )
    }

    private fun saveScreenshot(name: String) {
        val directory = File("build/reports/ui").apply { mkdirs() }
        compose.runOnIdle {
            val activity = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED).single()
            val view = activity.window.decorView
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            // Draw directly: PixelCopy's window redraw callback is unavailable in Robolectric.
            view.draw(Canvas(bitmap))
            File(directory, "$name.png").outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            bitmap.recycle()
        }
    }
}
