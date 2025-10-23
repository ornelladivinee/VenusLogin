package com.example.venuslogin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenida a Venus", fontSize = 28.sp, color = Color(0xFFD81B60))
        Spacer(modifier = Modifier.height(24.dp))
        Text("Aquí podrás acceder a tus funciones de salud femenina.", fontSize = 18.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("profesionales") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
        ) { Text("Ver Profesionales", color = Color.White) }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("historial") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) { Text("Ver Historial", color = Color.White) }
    }
}

