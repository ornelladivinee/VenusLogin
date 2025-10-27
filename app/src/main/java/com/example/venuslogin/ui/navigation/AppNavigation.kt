package com.example.venuslogin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.venuslogin.ui.login.LoginScreen
import com.example.venuslogin.ui.register.RegisterScreen
import com.example.venuslogin.ui.home.HomeScreen
import com.example.venuslogin.ui.professionals.ProfessionalsScreen
import com.example.venuslogin.ui.reserva.ReservaScreen
import com.example.venuslogin.ui.historial.HistorialScreen
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva

@Composable
fun AppNavigation(
    profesionales: List<Profesional>,
    reservas: List<Reserva>
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }

        composable("profesionales") {
            ProfessionalsScreen(profesionales) { prof ->
                navController.navigate("reserva/${prof.id}")
            }
        }

        composable(
            "reserva/{profId}",
            arguments = listOf(navArgument("profId") { type = NavType.IntType })
        ) { backStackEntry ->
            val profId = backStackEntry.arguments?.getInt("profId") ?: 0
            val prof = profesionales.find { it.id == profId }!!
            ReservaScreen(prof) { navController.popBackStack() }
        }

        composable("historial") { HistorialScreen(reservas = reservas) }

    }
}
