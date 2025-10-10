package com.example.venuslogin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.venuslogin.ui.login.LoginScreen
import com.example.venuslogin.ui.register.RegisterScreen
import com.example.venuslogin.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // el "control remoto" de la app

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }  // ← Aquí pasa navController
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen() }
    }

}
