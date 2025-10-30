package com.example.venuslogin.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venuslogin.R
import com.example.venuslogin.ui.theme.VenusLoginTheme

@Composable
fun HomeScreen(navController: NavHostController) {

    val venusPink = Color(0xFFD81B60)
    val lightPinkBg = Color(0xFFFCE4EC)

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.flores)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPinkBg)
            .padding(32.dp)
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Bienvenida a Venus",
                style = MaterialTheme.typography.headlineLarge,
                color = venusPink
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Aquí podrás acceder a tus funciones de salud femenina.",
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            ElevatedButton(
                onClick = { navController.navigate("profesionales") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = venusPink)
            ) {
                Icon(Icons.Default.MedicalServices, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ver Profesionales", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedButton(
                onClick = { navController.navigate("historial") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = venusPink)
            ) {
                Icon(Icons.Default.History, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ver Historial", color = Color.White)
            }
        }

        Text(
            text = "¡Venus seguirá mejorando para ti!",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    VenusLoginTheme {
        HomeScreen(navController = rememberNavController())
    }
}
