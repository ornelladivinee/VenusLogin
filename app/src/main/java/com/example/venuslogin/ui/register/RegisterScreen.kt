package com.example.venuslogin.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.venuslogin.R
import com.example.venuslogin.ui.theme.VenusLoginTheme

@Composable
fun RegisterScreen(navController: NavHostController) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmarClave by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.venus_logo),
            contentDescription = "Logo Venus",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

        // Título
        Text(
            text = "Registrarse",
            color = Color(0xFFD81B60),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de texto
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario", color = Color(0xFFD81B60), fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña", color = Color(0xFFD81B60), fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmarClave,
            onValueChange = { confirmarClave = it },
            label = { Text("Confirmar contraseña", color = Color(0xFFD81B60), fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de registro
        Button(
            onClick = {
                if (usuario.isBlank() || clave.isBlank() || confirmarClave.isBlank()) {
                    mensaje = "Por favor, completa todos los campos."
                } else if (clave != confirmarClave) {
                    mensaje = "Las contraseñas no coinciden."
                } else {
                    mensaje = "Registro exitoso. ¡Bienvenida, $usuario!"
                    navController.navigate("login")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
        ) {
            Text("Registrarse", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver al login
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Volver al Login", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje
        Text(text = mensaje, color = Color(0xFFD81B60), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    VenusLoginTheme {
        RegisterScreen(navController = rememberNavController())
    }
}

