package com.example.appmovil.ui.theme.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appmovil.R
// Importa R para acceder al layout: R.layout.fragment_login

/**
 * Fragmento que maneja la vista de Inicio de Sesión de HuertoHogar.
 * Su principal tarea es inflar el diseño XML simplificado.
 */
class LoginFragment : Fragment() {

    // Nota: Es recomendable usar ViewBinding para acceder a los elementos del layout (botones, campos de texto),
    // pero por ahora, solo inflaremos la vista de forma básica.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // La función CRÍTICA para que la interfaz se vea:
        // Carga el contenido del archivo XML 'fragment_login.xml'
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ** Aquí es donde se conectaría la lógica del ViewModel y los listeners de botones **

        // Ejemplo (Necesitarías ViewBinding o findViewById para esto):
        // val loginButton: Button = view.findViewById(R.id.btn_login)
        // loginButton.setOnClickListener {
        //     // Lógica para intentar iniciar sesión
        // }
    }
}