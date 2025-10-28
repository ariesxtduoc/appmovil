package com.example.appmovil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity // Importante: Cambiamos a AppCompatActivity

// Eliminamos todas las importaciones de Compose (ComponentActivity, setContent, Composable, etc.)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama a super.onCreate y maneja la restauración del estado si es necesario
        super.onCreate(savedInstanceState)

        // CRÍTICO: Carga la interfaz XML (activity_main.xml)
        // Este layout contiene el NavHostFragment que a su vez carga tu LoginFragment.
        setContentView(R.layout.activity_main)
    }
}

// Eliminamos las funciones @Composable (Greeting y GreetingPreview) ya que no usaremos Compose aquí.