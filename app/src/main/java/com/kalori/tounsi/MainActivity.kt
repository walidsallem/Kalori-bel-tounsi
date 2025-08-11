package com.kalori.tounsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.kalori.tounsi.ui.theme.KaloriTheme
import com.kalori.tounsi.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.kalori.tounsi.util.ReminderScheduler.scheduleAll(this)
        setContent {
            KaloriTheme {
                Surface {
                    KaloriNav()
                }
            }
        }
    }
}

@Composable
fun KaloriNav() {
    val nav = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val current = currentRoute(nav)
                listOf(
                    "home" to "نهارِي",
                    "add" to "زيد وجبة",
                    "history" to "تاريخ",
                    "profile" to "بروفيل"
                ).forEach { (route, label) ->
                    NavigationBarItem(
                        selected = current == route,
                        onClick = { nav.navigate(route) { launchSingleTop = true } },
                        label = { Text(label) },
                        icon = {}
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController = nav, startDestination = "onboard", modifier = Modifier.padding(padding)) {
            composable("onboard") { OnboardingScreen { nav.navigate("home") { popUpTo("onboard") { inclusive = true } } } }
            composable("home") { HomeScreen(nav) }
            composable("add") { AddFoodScreen() }
            composable("history") { HistoryScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
