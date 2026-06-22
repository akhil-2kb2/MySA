package com.example.mysa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mysa.ui.CallAssistScreen
import com.example.mysa.ui.CalendarScreen
import com.example.mysa.ui.LiveScreen
import com.example.mysa.ui.MainScaffold
import com.example.mysa.ui.MoreScreen
import com.example.mysa.ui.main.DashboardScreen

@Composable
fun MainNavigation() {
    var currentRoute by remember { mutableStateOf("dashboard") }

    MainScaffold(
        currentRoute = currentRoute,
        onNavigate = { currentRoute = it }
    ) { _ ->
        when (currentRoute) {
            "dashboard" -> DashboardScreen()
            "call_assist" -> CallAssistScreen()
            "live" -> LiveScreen()
            "calendar" -> CalendarScreen()
            "more" -> MoreScreen()
        }
    }
}
