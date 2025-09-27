import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.venuslogin.ui.theme.VenusLoginTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VenusLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFE4E1) // fondo rosado suave
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val usuarioValido = "venus"
    val claveValida = "1234"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Venus",
            color = Color(0xFFD81B60),
            fontSize = 36.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                mensaje = if (usuario == usuarioValido && clave == claveValida) {
                    "✅ Bienvenida a Venus"
                } else {
                    "❌ Usuario o contraseña incorrectos"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
        ) {
            Text(text = "Iniciar Sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensaje,
            color = Color(0xFFD81B60),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VenusLoginTheme {
        LoginScreen()
    }
}
