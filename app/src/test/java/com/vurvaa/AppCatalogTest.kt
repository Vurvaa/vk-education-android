package com.vurvaa

import com.vurvaa.data.AppCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppCatalogTest {
    @Test
    fun showcaseContainsExactlyThreePagesOfThreeDistinctApps() {
        assertEquals(3, AppCatalog.showcasePages.size)
        AppCatalog.showcasePages.forEach { assertEquals(3, it.size) }
        assertEquals(9, AppCatalog.showcasePages.flatten().map { it.id }.distinct().size)
        assertEquals(AppCatalog.apps, AppCatalog.showcasePages.flatten())
    }

    @Test
    fun everyCatalogIdResolvesToItsOwnDetails() {
        AppCatalog.apps.forEach { app ->
            assertEquals(app, AppCatalog.findApp(app.id))
        }
    }

    @Test
    fun missingAppDoesNotFallBackToAnotherApp() {
        assertNull(AppCatalog.findApp("missing-app"))
        assertNull(AppCatalog.findApp(null))
    }
}
