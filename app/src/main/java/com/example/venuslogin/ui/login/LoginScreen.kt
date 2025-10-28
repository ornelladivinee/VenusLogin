package com.example.venuslogin.ui.login

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
import com.example.venuslogin.ui.models.Usuario
import com.example.venuslogin.ui.theme.VenusLoginTheme

@Composable
fun LoginScreen(
    navController: NavHostController,
    usuarios: List<Usuario>
) {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
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
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Iniciar Sesión",
            color = Color(0xFFD81B60),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario o Correo", color = Color(0xFFD81B60), fontWeight = FontWeight.Bold) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña", color = Color(0xFFD81B60), fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Mensaje de error
        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Botón de Iniciar Sesión
        Button(
            onClick = {

                val usuarioEncontrado = usuarios.find {
                    (it.nombre == usuario || it.correo == usuario)
                            // Y si la clave es IGUAL A LA CLAVE
                            && it.clave == clave
                }


                if (usuarioEncontrado != null) {
                    mensaje = ""
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    val listaDeNombres = usuarios.joinToString(", ") { it.nombre }

                    mensaje = "Error. Lista: [$listaDeNombres]. Tú escribiste: [$usuario] y [$clave]"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
        ) {
            Text("Iniciar Sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón de Registrarse
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Registrarse", color = Color.White)
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VenusLoginTheme {
        LoginScreen(
            navController = rememberNavController(),
            usuarios = emptyList()
        )
    }
}
