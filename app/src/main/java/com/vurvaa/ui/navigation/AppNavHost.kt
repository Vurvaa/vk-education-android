package com.vurvaa.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vurvaa.R
import com.vurvaa.data.AppCatalog
import com.vurvaa.ui.appdetails.AppDetailsScreen
import com.vurvaa.ui.apps.AppsListScreen

private const val APPS_ROUTE = "apps"
private const val APP_ID = "appId"
private const val DETAILS_ROUTE = "app/{$APP_ID}"

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = APPS_ROUTE,
        modifier = modifier,
    ) {
        composable(APPS_ROUTE) {
            AppsListScreen(
                apps = AppCatalog.apps,
                onAppClick = { app ->
                    navController.navigate("app/${Uri.encode(app.id)}") {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(
            route = DETAILS_ROUTE,
            arguments = listOf(navArgument(APP_ID) { type = NavType.StringType }),
        ) { entry ->
            val app = AppCatalog.findApp(entry.arguments?.getString(APP_ID))
            if (app == null) {
                AppNotFound(onBackClick = { navController.popBackStack(APPS_ROUTE, false) })
            } else {
                AppDetailsScreen(
                    app = app,
                    onBackClick = { navController.popBackStack() },
                    onShowcaseAppClick = {
                        // The assignment sends every showcase item to the existing list.
                        // Popping preserves its scroll position and avoids duplicate lists.
                        navController.popBackStack(APPS_ROUTE, inclusive = false)
                    },
                )
            }
        }
    }
}

@Composable
private fun AppNotFound(onBackClick: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(stringResource(R.string.app_not_found))
            Button(onClick = onBackClick) {
                Text(stringResource(R.string.back_to_apps))
            }
        }
    }
}
